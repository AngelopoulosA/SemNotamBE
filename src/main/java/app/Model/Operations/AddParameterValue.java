package app.Model.Operations;

import app.Model.ComposedOperation;
import app.Model.Flora2.ParameterValue;

import java.util.Date;
import javax.persistence.Entity;

@Entity
public class AddParameterValue extends ComposedOperation {

    private String parameterId;

    public AddParameterValue(long parentId, Date executedAt, boolean isExecuted, String text, Long executedBy, String affectedElement) {
        super(parentId, executedAt, isExecuted, text, executedBy, affectedElement);
    }

    public AddParameterValue() {
    }

    @Override
    public Step[] getAllowedOperations() {
        return new Step[0];
    }

    public AddParameterValue(String parameterId, ParameterValue parameterValue) {
        super();
        this.setAffectedElement(parameterValue.getName());
        this.parameterId = parameterId;
    }

    @Override
    public String getVerb() {
        return "Add";
    }

    @Override
    public String getAffectedElementType() {
        return "ParameterValue";
    }
}
