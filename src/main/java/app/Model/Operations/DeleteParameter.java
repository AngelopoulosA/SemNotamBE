package app.Model.Operations;

import app.Model.ComposedOperation;

import javax.persistence.Entity;
import java.util.Date;

@Entity
public class DeleteParameter extends ComposedOperation {

    public DeleteParameter(Long parentId, Date executedAt, boolean isExecuted, String text, Long executedBy, String affectedElement) {
        super(parentId, executedAt, isExecuted, text, executedBy, affectedElement);
    }

    public DeleteParameter() {
    }

    @Override
    public String getVerb() {
        return "Delete";
    }

    @Override
    public String getAffectedElementType() {
        return "Parameter";
    }

    @Override
    public Step[] getAllowedOperations() {
        return new Step[] {
        };
    }
}
