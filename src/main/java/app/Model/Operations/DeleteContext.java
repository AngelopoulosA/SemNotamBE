package app.Model.Operations;

import app.Model.ComposedOperation;

import javax.persistence.Entity;
import java.util.Date;

@Entity
public class DeleteContext extends ComposedOperation {

    public DeleteContext(Long parentId, Date executedAt, boolean isExecuted, String text, Long executedBy, String affectedElement) {
        super(parentId, executedAt, isExecuted, text, executedBy, affectedElement);
    }

    public DeleteContext() {
    }

    @Override
    public String getVerb() {
        return "Delete";
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
