package app.Model.Operations;

import app.Model.ComposedOperation;
import app.Model.Flora2.Context;
import app.Model.Flora2.Rule;
import app.Model.Message;
import app.Model.Role;
import app.Repository.ContextDBRepository;
import app.Repository.Flora2Repository;

import javax.persistence.Entity;
import javax.persistence.Transient;
import java.util.LinkedList;
import java.util.List;

@Entity
public class MergeContext extends DeleteContext {
    @Transient
    private Context toContext;

    public MergeContext() {
    }

    public MergeContext(Context context, Context toContext) {
        super(context);
        this.toContext = toContext;
    }

    @Override
    public Step[] getAllowedOperations() {
        return new Step[] {
            new Step(new DecontextualizeRule(), false),
        };
    }

    @Override
    public String getVerb() {
        return "Merge";
    }

    @Override
    public String getAffectedElementType() {
        return "Context";
    }

    public Role canBeExecutedBy() {
        return Role.RepositoryAdmin;
    }

    @Override
    public List<Message> generateMessages(ContextDBRepository contextDBRepository) {
        List<Message> messages = new LinkedList<>();
        Message m = new Message();
        m.setTitle("Context " + context.getName() + " will be merged into Context " + toContext.getName());
        m.setContent("Please Decontextualize all Rules of Context " + context.getName() + " into Context " + toContext.getName());
        m.setAffectedElement(context.getName());
        m.setAffectedElementType("Context");
        m.setSender(getExecutedBy());
        m.getRecipients().addAll(context.getRuleDevelopers());
        m.setTime(getExecutedAt());
        messages.add(m);

        return messages;
    }

}
