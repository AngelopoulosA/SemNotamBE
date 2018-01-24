package app.Model.Operations;

import app.Model.ComposedOperation;
import app.Model.Flora2.Context;
import app.Model.Flora2.Rule;
import app.Model.InvalidOperationException;
import app.Model.Message;

import javax.persistence.Entity;
import javax.persistence.Transient;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Entity
public class AddRule extends ComposedOperation {
    @Transient
    private Context context;
    @Transient
    private Rule rule;
    private String contextId;

    public AddRule() {
    }

    public AddRule(Context context, Rule rule) {
        super();
        this.context = context;
        this.rule = rule;
        this.setAffectedElement(rule.getId());
        this.contextId = context.getName();
    }

    public Step[] getAllowedOperations() {
        return new Step[] {
                new Step(EditRule.class, true),
                new Step(DeleteRule.class, true),
        };
    }

    @Override
    public String getVerb() {
        return "Add";
    }

    @Override
    public String getAffectedElementType() {
        return "Rule";
    }

    @Override
    public List<Message> generateMessages() {
        List<Message> messages = new LinkedList<>();
        if (!(getParent() instanceof  ContextualizeRule)) {
            String ruleId = getAffectedElement();
            List<Context> childContexts = context.getChildrenFlat();
            if(rule == null) {
                throw new InvalidOperationException();
            }
            String ruleText = rule.getId() + " : " + rule.getBody();

            for (Context c : childContexts) {
                Message m = new Message();
                m.setTitle("Rule " + ruleId + " was added to Parent Context " + context.getName());
                m.setContent(ruleText);
                m.setAffectedElement(c.getName());
                m.setAffectedElementType("Context");
                m.setSender(getExecutedBy());
                m.getRecipients().addAll(c.getRuleDevelopers());
                m.setTime(getExecutedAt());
                messages.add(m);
            }
        }

        return messages;
    }
}
