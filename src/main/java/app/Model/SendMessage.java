package app.Model;

import app.Model.Operation;
import javax.persistence.Entity;
import java.util.Date;

@Entity
public class SendMessage extends Operation {
    @Override
    public String getAbstractType() {
        return "SendMessage";
    }

    @Override
    public String getVerb() {
        return "Send";
    }

    @Override
    public String getAffectedElementType() {
        return "Message";
    }

    public SendMessage() {
    }

    public SendMessage(Long parentId, Date executedAt, boolean isExecuted, String text, Long userId, String affectedElement) {
        super(parentId, executedAt, isExecuted, text, userId, affectedElement);
    }
}
