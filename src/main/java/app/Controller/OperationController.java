package app.Controller;

import app.Model.*;
import app.Model.Operations.*;
import app.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping(path="/operations")
@CrossOrigin
public class OperationController {
    private final ContextDBRepository contextDBRepository;
    private final UserRepository userRepository;
    private final OperationRepository operationRepository;
    private final ComposedOperationRepository composedOperationRepository;

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Autowired
    public OperationController(ContextDBRepository contextDBRepository, UserRepository userRepository, OperationRepository operationRepository, ComposedOperationRepository composedOperationRepository) {
        this.contextDBRepository = contextDBRepository;
        this.userRepository = userRepository;
        this.operationRepository = operationRepository;
        this.composedOperationRepository = composedOperationRepository;
    }

    @GetMapping(path="/allowed")
    public @ResponseBody
    Map<String,Boolean> getAllowedOperations (@RequestHeader("User") Long userId) {
        User user = userRepository.findOne(userId);
        ComposedOperation pending = getPendingOperation();
        ComposedOperation root = composedOperationRepository.findFirstByParentIsNull();
        boolean fatalError = root != null && root.isFatalErrorSomewhere();
        Map<String,Boolean> allowedOperations;
        if (root == null){
            allowedOperations = getAllOperations().stream().collect(Collectors.toMap(o -> o.getConcreteType(), o -> o.canBeExecutedBy() == user.getRole()));
        } else if (!fatalError && pending != null && !pending.isExecuted()) {
            allowedOperations = getAllOperations().stream().collect(Collectors.toMap(o -> o.getConcreteType(), o -> false));
            for (Step allowedStep: pending.getAllowedOperations()) {
                allowedOperations.put(allowedStep.getOperation(),allowedStep.getOperationClass().canBeExecutedBy() == user.getRole());
            }
        } else {
            allowedOperations = getAllOperations().stream().collect(Collectors.toMap(o -> o.getConcreteType(), o -> false));
        }

        boolean transactionOperationsAllowed = (user.getRole() == Role.RepositoryAdmin) || (root != null && root.getUserId() == userId);
        allowedOperations.put("CommitTransaction", !fatalError && root != null && root.isExecuted() && transactionOperationsAllowed);
        allowedOperations.put("RollbackTransaction", transactionOperationsAllowed);
        return allowedOperations;
    }

    @GetMapping(path="")
    public @ResponseBody
    Iterable<Operation> getAllCurrentOperations () {
        return operationRepository.findAll();
    }

    @GetMapping(path="/hierarchy")
    public @ResponseBody
    ComposedOperation getCurrentOperationsHierarchy () {
        return composedOperationRepository.findFirstByParentIsNull();
    }

    @GetMapping(path="/pending")
    public @ResponseBody
    ComposedOperation getPendingOperation () {
        ComposedOperation root = composedOperationRepository.findFirstByParentIsNull();
        if(root != null) {
            return root.findCurrentOperation();
        }
        return null;
    }

    @PostMapping(path="/rollback")
    public @ResponseBody
    ComposedOperation rollbackTransaction () {
        try {
            Flora2Repository.rollbackTransaction();
            rollbackSpecialOperations();
            truncateDB();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private void rollbackSpecialOperations() {
        Iterable<Operation> allOperations = operationRepository.findAll();
        for (Operation o : allOperations) {
            if (o instanceof AddContext) {
                String context = o.getAffectedElement();
                try {
                    contextDBRepository.delete(context);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (o instanceof DeleteContext) {
                String context = o.getAffectedElement();
                try {
                    ContextDB contextDB = new ContextDB(context);
                    Long[] ruleDeveloperIDs = ((DeleteContext) o).getRuleDevelopers();
                    for (Long ruleDeveloperId : ruleDeveloperIDs) {
                        contextDB.getRuleDevelopers().add(userRepository.findOne(ruleDeveloperId));
                    }
                    contextDBRepository.save(contextDB);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void truncateDB() {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.createNativeQuery("SET FOREIGN_KEY_CHECKS=0;").executeUpdate();
        entityManager.createNativeQuery("TRUNCATE TABLE operation").executeUpdate();
        entityManager.createNativeQuery("TRUNCATE TABLE message").executeUpdate();
        entityManager.createNativeQuery("TRUNCATE TABLE message_recipients").executeUpdate();
        entityManager.createNativeQuery("SET FOREIGN_KEY_CHECKS=1;").executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @PostMapping(path="/commit")
    public @ResponseBody
    ComposedOperation commitTransaction () {
        ComposedOperation root = composedOperationRepository.findFirstByParentIsNull();
        if(root != null && root.isExecuted()) {
            try (Flora2Repository fl = new Flora2Repository()) {
                fl.commitTransaction();
                truncateDB();
                return null;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return getCurrentOperationsHierarchy();
    }

    private List<ComposedOperation> getAllOperations() {
        return Arrays.asList(
                new AddContext(),
                new AddParameter(),
                new AddParameterValue(),
                new AddRule(),
                new AddRuleDeveloper(),
                new ContextualizeRule(),
                new DecontextualizeRule(),
                new DeleteContext(),
                new DeleteParameter(),
                new DeleteParameterValue(),
                new DeleteRule(),
                new DeleteRuleDeveloper(),
                new EditRule(),
                new MergeContext(),
                new SplitContext(),
                new UpdateContext(),
                new UpdateParameter()
        );
    }

}
