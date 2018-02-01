package app.Model.Operations;

import app.Model.ComposedOperation;
import app.Model.Flora2.Context;
import app.Model.Message;
import app.Model.Role;
import app.Model.SendMessage;
import app.Repository.ContextDBRepository;
import app.Repository.Flora2Repository;

import javax.persistence.Entity;
import javax.persistence.Transient;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class DeleteContext extends ComposedOperation {

    public DeleteContext(Long parentId, Date executedAt, boolean isExecuted, String text, Long executedBy, String affectedElement) {
        super(parentId, executedAt, isExecuted, text, executedBy, affectedElement);
    }

    @Transient
    protected Context context;
    private Long[] ruleDevelopers;

    public DeleteContext(Context context) {
        super();
        this.setAffectedElement(context.getName());
        this.context = context;
        this.ruleDevelopers = context.getRuleDevelopers().stream().map(rd -> rd.getId()).collect(Collectors.toList()).toArray(new Long[0]);
    }

    public DeleteContext() {
    }

    public Long[] getRuleDevelopers() {
        return ruleDevelopers;
    }

    @Override
    public String getVerb() {
        return "Delete";
    }

    @Override
    public String getAffectedElementType() {
        return "Context";
    }

    @Override
    public Step[] getAllowedOperations() {
        return new Step[] {
                new Step(new DeleteRule(), true),
                new Step(new EditRule(), true),
                new Step(new AddRule(), true),
        };
    }

    @Override
    public List<Message> generateMessages(ContextDBRepository contextDBRepository) {
        List<Message> messages = new LinkedList<>();
        if (this.context.getRules().size() != 0) {

            List<Context> childContexts = context.getChildrenFlat();
            for (Context c : childContexts) {
                Message m = new Message();
                m.setTitle("Parent Context " + context.getName() + " will be deleted");
                m.setAffectedElement(c.getName());
                m.setAffectedElementType("Context");
                m.setSender(getExecutedBy());
                m.getRecipients().addAll(c.getRuleDevelopers());
                m.setTime(getExecutedAt());
                messages.add(m);
            }

            Message m = new Message();
            m.setTitle("Context " + context.getName() + " will be deleted");
            m.setAffectedElement(context.getName());
            m.setAffectedElementType("Context");
            m.setSender(getExecutedBy());
            m.getRecipients().addAll(context.getRuleDevelopers());
            m.setTime(getExecutedAt());
            messages.add(m);

        }
        return messages;
    }

    @Override
    public boolean onExecuted(ContextDBRepository contextDBRepository) {
        String id = getAffectedElement();
        try (Flora2Repository fl = new Flora2Repository()) {
            boolean result = fl.delCtx(id, true);
            if(result) {
                contextDBRepository.delete(id);
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Role canBeExecutedBy() {
        return Role.RepositoryAdmin;
    }
}
