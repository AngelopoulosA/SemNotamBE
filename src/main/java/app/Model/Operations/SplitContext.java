package app.Model.Operations;

import app.Model.ComposedOperation;
import app.Model.Flora2.Context;
import app.Model.Message;
import app.Repository.ContextDBRepository;
import app.Repository.Flora2Repository;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
public class SplitContext extends ComposedOperation {

    @Transient
    private Context context;

    public SplitContext(Context context) {
        super();
        this.setAffectedElement(context.getName());
        this.context = context;
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
            new Step(UpdateParameter.class, true),
            new Step(AddContext.class, false)
        };
    }

    @Override
    public List<Message> generateMessages() {
        List<Message> messages = new LinkedList<>();
        Message m = new Message();
        m.setTitle("Context " + context.getName() + " will be splitted");
        m.setContent("Acknowledge this Message to complete the Split Operation.");
        m.setAffectedElement(context.getName());
        m.setAffectedElementType("Context");
        m.setSender(getExecutedBy());
        m.getRecipients().add(getExecutedBy());
        m.setTime(getExecutedAt());
        messages.add(m);

        return messages;
    }
}
