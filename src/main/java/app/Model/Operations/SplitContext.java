package app.Model.Operations;

import app.Model.ComposedOperation;

import java.util.Date;
import java.util.List;
import javax.persistence.Entity;

@Entity
public class SplitContext extends ComposedOperation {

    public SplitContext(Long parentId, Date executedAt, boolean isExecuted, String text, Long executedBy, String affectedElement) {
        super(parentId, executedAt, isExecuted, text, executedBy, affectedElement);
    }

    public SplitContext() {
    }

    @Override
    public String getVerb() {
        return "Split";
    }

    @Override
    public String getAffectedElementType() {
        return "Context";
    }

    @Override
    public Step[] getAllowedOperations() {
        return new Step[] {
            new Step(AddParameterValue.class, true),
            new Step(AddContext.class, false)
        };
    }
}
