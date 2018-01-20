package app.Model.Operations;

import app.Model.ComposedOperation;

import javax.persistence.Entity;
import java.util.Date;

@Entity
public class UpdateContext extends ComposedOperation {

    public UpdateContext(Long parentId, Date executedAt, boolean isExecuted, String text, Long executedBy, String affectedElement) {
        super(parentId, executedAt, isExecuted, text, executedBy, affectedElement);
    }

    public UpdateContext() {
    }

    @Override
    public String getVerb() {
        return "Update";
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
