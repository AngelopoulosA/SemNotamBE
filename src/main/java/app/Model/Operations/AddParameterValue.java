package app.Model.Operations;

import app.Model.AtomicOperation;
import app.Model.ComposedOperation;
import java.util.Date;
import javax.persistence.Entity;

@Entity
public class AddParameterValue extends AtomicOperation {

    public AddParameterValue(long parentId, Date executedAt, boolean isExecuted, String text, Long executedBy, String affectedElement) {
        super(parentId, executedAt, isExecuted, text, executedBy, affectedElement);
    }

    public AddParameterValue() {
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
