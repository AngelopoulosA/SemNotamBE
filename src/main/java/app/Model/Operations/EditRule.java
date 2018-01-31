package app.Model.Operations;

import app.Model.ComposedOperation;
import app.Model.Flora2.Context;
import app.Model.Flora2.Rule;
import app.Model.InvalidOperationException;
import app.Model.Message;
import app.Model.Role;

import javax.persistence.Entity;
import javax.persistence.Transient;
import java.util.LinkedList;
import java.util.List;

@Entity
public class EditRule extends ComposedOperation {

    @Transient
    private Context context;
    @Transient
    private Rule rule;
    private String contextId;

    public EditRule() {
    }

    public EditRule(Context context, Rule rule) {
        super();
        this.context = context;
        this.rule = rule;
        this.setAffectedElement(rule.getId());
        this.contextId = context.getName();
    }

    @Override
    public Step[] getAllowedOperations() {
        return new Step[] {
                new Step(new EditRule(), true),
                new Step(new AddRule(), true),
                new Step(new DeleteRule(), true),
        };
    }

    @Override
    public String getVerb() {
        return "Edit";
    }

    @Override
    public String getAffectedElementType() {
        return "Rule";
    }

    @Override
    public List<Message> generateMessages() {
        List<Message> messages = new LinkedList<>();
            String ruleId = getAffectedElement();
            List<Context> childContexts = context.getChildrenFlat();
            Rule rule = context.getRules().stream().filter(r -> r.getId().equals(ruleId)).findFirst().orElseGet(null);
            if(rule == null) {
                throw new InvalidOperationException();
            }
            String ruleText = rule.getId() + " : " + rule.getBody();

            for (Context c : childContexts) {
                Message m = new Message();
                m.setTitle("Rule " + ruleId + " was edited in Parent Context " + context.getName());
                m.setContent(ruleText);
                m.setAffectedElement(c.getName());
                m.setAffectedElementType("Context");
                m.setSender(getExecutedBy());
                m.getRecipients().addAll(c.getRuleDevelopers());
                m.setTime(getExecutedAt());
                messages.add(m);
            }

        return messages;
    }

    public Role canBeExecutedBy() {
        return Role.RuleDeveloper;
    }
}
