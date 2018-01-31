package app.Model.Operations;

import app.Model.ComposedOperation;
import app.Model.Flora2.Context;
import app.Model.Flora2.Rule;
import app.Model.Message;
import app.Repository.ContextDBRepository;
import app.Repository.Flora2Repository;

import javax.persistence.Entity;
import javax.persistence.Transient;
import java.util.LinkedList;
import java.util.List;

@Entity
public class ContextualizeRule extends ComposedOperation {

    @Transient
    private Context context;
    @Transient
    private Context toContext;
    @Transient
    private Rule rule;
    private String contextId;

    public ContextualizeRule() {
    }

    public ContextualizeRule(Rule rule, Context context, Context toContext) {
        this.context = context;
        this.toContext = toContext;
        this.rule = rule;
        this.setAffectedElement(rule.getId());
        this.contextId = context.getName();
    }

    public Step[] getAllowedOperations() {
        return new Step[] {
                new Step(EditRule.class, true),
                new Step(AddRule.class, true),
        };
    }

    @Override
    public String getVerb() {
        return "Contextualize";
    }

    @Override
    public String getAffectedElementType() {
        return "Rule";
    }

    @Override
    public List<Message> generateMessages() {
        List<Message> messages = new LinkedList<>();

        Message m = new Message();
        m.setTitle("Rule " + rule.getId() + " should be contextualized and added to Context " + toContext.getName());
        m.setContent("Rule " + rule.getId() + " of Context " + context.getName() + " should be contextualized and added to Context " + toContext.getName() + ": \n"+rule.getId() + " : " + rule.getBody());

        m.setAffectedElement(toContext.getName());
        m.setAffectedElementType("Context");
        m.setSender(getExecutedBy());
        m.getRecipients().addAll(toContext.getRuleDevelopers());
        m.setTime(getExecutedAt());
        messages.add(m);

       return messages;
    }

    @Override
    public boolean onExecuted(ContextDBRepository contextDBRepository) {
        String id = getAffectedElement();
        try (Flora2Repository fl = new Flora2Repository()) {
            boolean result = fl.delRule(contextId, id);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
