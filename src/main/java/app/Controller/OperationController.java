package app.Controller;

import app.Model.*;
import app.Model.Operations.*;
import app.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
        Map<String,Boolean> allowedOperations;
        if(pending != null) {
            allowedOperations = getAllOperations().stream().collect(Collectors.toMap(o -> o.getSimpleName(), o -> false));
            for (Step allowedStep: pending.getAllowedOperations()) {
                allowedOperations.put(allowedStep.getOperation(),true);
            }
        } else {
            allowedOperations = getAllOperations().stream().collect(Collectors.toMap(o -> o.getSimpleName(), o -> true));
        }

        allowedOperations.put("CommitTransaction", false);
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
