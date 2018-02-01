package app.Model.Operations;

import app.Model.ComposedOperation;
import app.Model.Flora2.Parameter;
import app.Model.Flora2.ParameterValue;
import app.Model.Message;
import app.Model.Role;
import app.Repository.ContextDBRepository;

import javax.persistence.Entity;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Entity
public class AddParameter extends ComposedOperation {

    public AddParameter() {
    }

    public AddParameter(Parameter parameter) {
        super();
        this.setAffectedElement(parameter.getName());
    }

    @Override
    public String getVerb() {
        return "Add";
    }

    @Override
    public String getAffectedElementType() {
        return "Parameter";
    }

    @Override
    public Step[] getAllowedOperations() {
        return new Step[] {
                new Step(new AddParameterValue(), true),
        };
    }

    public Role canBeExecutedBy() {
        return Role.RepositoryAdmin;
    }

    @Override
    public List<Message> generateMessages(ContextDBRepository contextDBRepository) {

        List<Message> messages = new LinkedList<>();
        Message m = new Message();
        m.setTitle("Parameter " + getAffectedElement() + " was added");
        m.setContent("Acknowledge this Message to complete the AddParameter Operation.");
        m.setAffectedElement(getAffectedElement());
        m.setAffectedElementType("Parameter");
        m.setSender(getExecutedBy());
        m.getRecipients().add(getExecutedBy());
        m.setTime(getExecutedAt());
        messages.add(m);

        return messages;
    }
}
