package app.Model;

import app.Model.Operation;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.util.Date;

/**
 * Special Operation that defines when a Message is sent for a given Parent Operation
 */
@Entity
public class SendMessage extends Operation {
    @OneToOne()
    private Message message;

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

    public SendMessage(Long parentId, String text, Long userId, Message message) {
        super(parentId, message.getTime(), false, text, userId, message.getId().toString());
        this.message = message;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }
}
