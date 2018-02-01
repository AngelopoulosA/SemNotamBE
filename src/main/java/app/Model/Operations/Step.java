package app.Model.Operations;

import app.Model.ComposedOperation;
import app.Model.Operation;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class Step {
    @JsonIgnore
    private ComposedOperation operation;
    private boolean optional;

    public Step(ComposedOperation operation, boolean optional) {
        this.operation = operation;
        this.optional = optional;
    }

    public ComposedOperation getOperationClass() {
        return operation;
    }

    public void setOperation(ComposedOperation operation) {
        this.operation = operation;
    }

    public boolean isOptional() {
        return optional;
    }

    public void setOptional(boolean optional) {
        this.optional = optional;
    }

    public String getOperation() {
        return operation.getConcreteType();
    }
}
