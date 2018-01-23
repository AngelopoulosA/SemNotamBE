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
    Map<String,Boolean> getAllowedOperations () {
        ComposedOperation pending = getPendingOperation();
        ComposedOperation root = composedOperationRepository.findFirstByParentIsNull();
        Map<String,Boolean> allowedOperations;
        if (root == null){
            allowedOperations = getAllOperations().stream().collect(Collectors.toMap(o -> o.getSimpleName(), o -> true));
        } else if (pending != null && !pending.isExecuted()) {
            allowedOperations = getAllOperations().stream().collect(Collectors.toMap(o -> o.getSimpleName(), o -> false));
            for (Step allowedStep: pending.getAllowedOperations()) {
                allowedOperations.put(allowedStep.getOperation(),true);
            }
        } else {
            allowedOperations = getAllOperations().stream().collect(Collectors.toMap(o -> o.getSimpleName(), o -> false));
        }
        allowedOperations.put("CommitTransaction", root != null && root.isExecuted());
        allowedOperations.put("RollbackTransaction", true);
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
        try (Flora2Repository fl = new Flora2Repository()) {
            fl.rollbackTransaction();
            truncateDB();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private void truncateDB() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.createNativeQuery("SET FOREIGN_KEY_CHECKS=0;").executeUpdate();
        entityManager.createNativeQuery("TRUNCATE TABLE operation").executeUpdate();
        entityManager.createNativeQuery("TRUNCATE TABLE message").executeUpdate();
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

    private List<Class> getAllOperations() {
        return Arrays.asList(
                AddContext.class,
                AddParameter.class,
                AddParameterValue.class,
                AddRule.class,
                AddRuleDeveloper.class,
                ContextualizeRule.class,
                DecontextualizeRule.class,
                DeleteContext.class,
                DeleteParameter.class,
                DeleteParameterValue.class,
                DeleteRule.class,
                DeleteRuleDeveloper.class,
                EditRule.class,
                MergeContext.class,
                SplitContext.class,
                UpdateContext.class,
                UpdateParameter.class
        );
    }

}
