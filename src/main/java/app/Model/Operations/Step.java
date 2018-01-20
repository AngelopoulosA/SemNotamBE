package app.Model.Operations;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Step {
    @JsonIgnore
    private Class operationClass;
    private boolean optional;

    public Step(Class operation, boolean optional) {
        operationClass = operation;
        this.optional = optional;
    }

    public Class getOperationClass() {
        return operationClass;
    }

    public void setOperation(Class operation) {
        operationClass = operation;
    }

    public boolean isOptional() {
        return optional;
    }

    public void setOptional(boolean optional) {
        this.optional = optional;
    }

    public String getOperation() {
        return operationClass.getSimpleName();
    }
}
