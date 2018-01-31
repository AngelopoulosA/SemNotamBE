package app.Model.Operations;

import app.Model.ComposedOperation;
import app.Model.Flora2.Context;
import app.Model.Message;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
public class AddContext extends ComposedOperation {
    @Transient
    private Context context;

    public AddContext(Long parentId, Date executedAt, boolean isExecuted, String text, Long executedBy, String affectedElement) {
        super(parentId, executedAt, isExecuted, text, executedBy, affectedElement);
    }

    public AddContext() {
    }

    public AddContext(Context context) {
        super();
        this.setAffectedElement(context.getName());
        this.context = context;
    }

    @Override
    public String getVerb() {
        return "Add";
    }

    @Override
    public String getAffectedElementType() {
        return "Context";
    }

    @Override
    public Step[] getAllowedOperations() {
        if (getParent() instanceof SplitContext) {
            return new Step[] {
                    new Step(ContextualizeRule.class, true),
            };
        }
        return new Step[] {
                new Step(DeleteRule.class, true),
                new Step(EditRule.class, true),
                new Step(AddRule.class, true),
        };
    }

    @Override
    public List<Message> generateMessages() {
        List<Message> messages = new LinkedList<>();

        Message m = new Message();
        m.setTitle("Context " + context.getName() + " was added");
        m.setAffectedElement(context.getName());
        m.setAffectedElementType("Context");
        m.setSender(getExecutedBy());
        m.getRecipients().addAll(context.getRuleDevelopers());
        m.setTime(getExecutedAt());
        messages.add(m);

        if (getParent() instanceof SplitContext) {

            String parentContextName = getParent().getAffectedElement();
            Context parentContext = context.getParents().stream().filter(c -> c.getName().equals(parentContextName)).findFirst().get();

            m.setContent("Context " + context.getName() + " was added for splitting Context " + parentContextName);

            Message m1 = new Message();
            m1.setTitle("Context " + context.getName() + " was added below ");
            m1.setContent("Context " + context.getName() + " was added below Context " + parentContextName + ".\nPlease Contextualize Rules of Context");
            m1.setAffectedElement(parentContextName);
            m1.setAffectedElementType("Context");
            m1.setSender(getExecutedBy());
            m1.getRecipients().addAll(parentContext.getRuleDevelopers());
            m1.setTime(getExecutedAt());
            messages.add(m1);
        }


        return messages;
    }
}
