package app.Model.Operations;

import app.Model.ComposedOperation;
import app.Model.Flora2.Context;
import app.Model.Flora2.Rule;
import app.Model.InvalidOperationException;
import app.Model.Message;
import app.Repository.Flora2Repository;

import javax.persistence.Entity;
import javax.persistence.Transient;
import java.util.LinkedList;
import java.util.List;

@Entity
public class DeleteRule extends ComposedOperation {

    @Transient
    private Context context;
    @Transient
    private String ruleId;

    public DeleteRule() {
    }

    public DeleteRule(Context context, String ruleId) {
        super();
        this.setAffectedElement(context.getName());
        this.context = context;
        this.ruleId = ruleId;
    }

    public Step[] getAllowedOperations() {
        return new Step[] {
                new Step(EditRule.class, true),
                new Step(AddRule.class, true),
        };
    }

    @Override
    public String getVerb() {
        return "Delete";
    }

    @Override
    public String getAffectedElementType() {
        return "Rule";
    }

    @Override
    public List<Message> generateMessages() {
        List<Message> messages = new LinkedList<>();
        List<Context> childContexts = context.getChildrenFlat();
        Rule rule = context.getRules().stream().filter(r -> r.getId().equals(ruleId)).findFirst().orElseGet(null);
        if(rule == null) {
            throw new InvalidOperationException();
        }
        String ruleText = rule.getId() + " : " + rule.getBody();

        for (Context c : childContexts) {
            Message m = new Message();
            m.setTitle("Rule " + ruleId + " of Parent Context " + context.getName() + " will be deleted");
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

}
