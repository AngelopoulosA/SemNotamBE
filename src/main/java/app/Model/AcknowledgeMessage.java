package app.Model;

import app.Model.Operation;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.util.Date;

/**
 * Special Operation that defines when a Message is Acknowledged.
 * Necessary only for the FE.
 */
@Entity
public class AcknowledgeMessage extends Operation {
    @OneToOne()
    private Message message;

    @Override
    public String getAbstractType() {
        return "AcknowledgeMessage";
    }

    @Override
    public String getVerb() {
        return "Acknowledge";
    }

    @Override
    public String getAffectedElementType() {
        return "Message";
    }

    public AcknowledgeMessage() {
    }

    public AcknowledgeMessage(Long parentId, Date executedAt, Long userId, Message m) {
        super(parentId, executedAt, true, "", userId, m.getId().toString());
        this.message = m;
    }
}
