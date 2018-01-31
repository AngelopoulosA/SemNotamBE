package app.Model;

import app.Model.Operations.Step;
import app.Repository.ContextDBRepository;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

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

    public abstract Step[] getAllowedOperations();

    public boolean checkIfAllowed (ComposedOperation operation, User user) {
        Step[] allowedOperations = getAllowedOperations();
        boolean generally = Arrays.asList(allowedOperations).stream()
                .filter(s -> s.getOperationClass().getClass() == operation.getClass())
                .findFirst()
                .isPresent();
        return generally && operation.canBeExecutedBy() == user.getRole();
    }

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

    public List<Message> generateMessages() {
        return new LinkedList<>();
    }

    public boolean onExecuted(ContextDBRepository contextDBRepository) {
        return true;
    }

    public abstract Role canBeExecutedBy();
}
