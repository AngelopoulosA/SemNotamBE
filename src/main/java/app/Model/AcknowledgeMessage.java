package app.Model;

import app.Model.Operation;

import javax.persistence.Entity;

@Entity
public class AcknowledgeMessage extends Operation {
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
}
