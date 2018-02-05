package app.Model;

import javax.persistence.Entity;
import java.util.Date;

/**
 * Operation that never consists of any other Operations.
 * Currently not used.
 */
@Entity
public abstract class AtomicOperation extends Operation  {

    public AtomicOperation(long parentId, Date executedAt, boolean isExecuted, String text, Long executedBy, String affectedElement) {
        super(parentId, executedAt, isExecuted, text, executedBy, affectedElement);
    }

    public AtomicOperation() {
    }

    @Override
    public String getAbstractType() {
        return "AtomicOperation";
    }
}
