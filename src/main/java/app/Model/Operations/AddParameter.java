package app.Model.Operations;

import app.Model.ComposedOperation;
import app.Model.Flora2.ParameterValue;

import javax.persistence.Entity;
import java.util.Date;

@Entity
public class AddParameter extends ComposedOperation {

    public AddParameter(Long parentId, Date executedAt, boolean isExecuted, String text, Long executedBy, String affectedElement) {
        super(parentId, executedAt, isExecuted, text, executedBy, affectedElement);
    }

    public AddParameter() {
    }

    @Override
    public String getVerb() {
        return "Add";
    }

    @Override
    public String getAffectedElementType() {
        return "Parameter";
    }

    @Override
    public Step[] getAllowedOperations() {
        return new Step[] {
                new Step(AddParameterValue.class, true),
        };
    }
}
