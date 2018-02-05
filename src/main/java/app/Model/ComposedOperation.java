package app.Model;

import app.Model.Operations.Step;
import app.Repository.ContextDBRepository;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Base Class for all Composed Operations.
 * Composed Operations can consist of other Operations - even if those are just SendMessage Operations.
 * Currently jsut Composed Operations are used and no AtomicOperations due to the fact that every Operation can have other following sub-Operations.
 */
@Entity
public abstract class ComposedOperation extends Operation {
    @OneToMany(mappedBy = "parent")
    private List<Operation> operations;

    public ComposedOperation(Long parentId, Date executedAt, boolean isExecuted, String text, Long executedBy, String affectedElement) {
        super(parentId, executedAt, isExecuted, text, executedBy, affectedElement);
        this.operations = new LinkedList<>();
    }

    public ComposedOperation() {
        this.operations = new LinkedList<>();
    }

    @Override
    public String getAbstractType() {
        return "ComposedOperation";
    }

    public List<Operation> getOperations() {
        return operations;
    }

    public void setOperations(List<Operation> operations) {
        this.operations = operations;
    }

    /**
     * Has to be overwritten by the concrete Operations.
     * Defines which other (Sub-)Operations are allowed when this Operation is pending.
     * @return
     */
    @JsonIgnore
    public abstract Step[] getAllowedOperations();

    /**
     * Checks whether a given Operation can be executed when this Operation is pending
     * and also if the specified User is authorized for this.
     * @param operation the Operation which shall be checked
     * @param user the User executing the given Operation
     * @return
     */
    public boolean checkIfAllowed (ComposedOperation operation, User user) {
        Step[] allowedOperations = getAllowedOperations();
        boolean generally = Arrays.asList(allowedOperations).stream()
                .filter(s -> s.getOperationClass().getClass() == operation.getClass())
                .findFirst()
                .isPresent();
        return generally && operation.canBeExecutedBy() == user.getRole();
    }

    /**
     * Find sthe currently pending Operation amongst the Hierarchy of Operations starting with this Operation
     * @return
     */
    public ComposedOperation findCurrentOperation() {
        for (Operation o: operations) {
            if(o.isExecuted() == false) {
                if(o instanceof ComposedOperation) {
                    return ((ComposedOperation) o).findCurrentOperation();
                }
            }
        }

        return this;
    }

    /**
     * Should mostly be overwritten by the concrete Operations.
     * Defines which Messages are to be sent to which Users when this Operation is started
     * @param contextDBRepository
     * @return
     */
    public List<Message> generateMessages(ContextDBRepository contextDBRepository) {
        return new LinkedList<>();
    }

    /**
     * Can be overwritten by the concrete Operations.
     * Hook that allows to execute Code when the Operation is finished (Therefore all Messages are Acknowledged)
     * @param contextDBRepository
     * @return true when the Execution was successful
     */
    public boolean onExecuted(ContextDBRepository contextDBRepository) {
        return true;
    }

    /**
     * Has to be overwritten by the concrete Operations.
     * Defines by Users of which Role this Operation can be executed
     * @return
     */
    public abstract Role canBeExecutedBy();

    /**
     * Checks if somewhere in the Tree of Operations there is one with a FatalError set.
     * @return
     */
    public boolean isFatalErrorSomewhere() {
        boolean fatalError = this.isFatalError();
        for (Operation o: operations) {
            if(o.isExecuted() == false) {
                if (o instanceof ComposedOperation) {
                    fatalError = fatalError || ((ComposedOperation) o).isFatalErrorSomewhere();
                }
            }
        }
        return fatalError;
    }
}
