package app.Model.Operations;

import app.Model.ComposedOperation;

import java.util.Date;
import javax.persistence.Entity;

@Entity
public class AddContext extends ComposedOperation {

    public AddContext(Long parentId, Date executedAt, boolean isExecuted, String text, Long executedBy, String affectedElement) {
        super(parentId, executedAt, isExecuted, text, executedBy, affectedElement);
    }

    public AddContext() {
    }

    @Override
    public String getVerb() {
        return "Add";
    }

    @Override
    public String getAffectedElementType() {
        return "Context";
    }

    @Override
    public Step[] getAllowedOperations() {
        return new Step[] {
                new Step(DeleteRule.class, true),
                new Step(EditRule.class, true),
                new Step(AddRule.class, true),
        };
    }
}
