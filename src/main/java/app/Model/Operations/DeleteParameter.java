package app.Model.Operations;

import app.Model.ComposedOperation;
import app.Model.Flora2.Context;
import app.Model.Flora2.Parameter;
import app.Model.Message;
import app.Model.Role;
import app.Repository.ContextDBRepository;
import app.Repository.Flora2Repository;

import javax.persistence.Entity;
import javax.persistence.Transient;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Entity
public class DeleteParameter extends ComposedOperation {

    @Transient
    Parameter parameter;

    public DeleteParameter() {
    }

    public DeleteParameter(Parameter parameter) {
        super();
        this.setAffectedElement(parameter.getName());
        this.parameter = parameter;
    }

    @Override
    public String getVerb() {
        return "Delete";
    }

    @Override
    public String getAffectedElementType() {
        return "Parameter";
    }

    @Override
    public Step[] getAllowedOperations() {
        return new Step[] {
                new Step(new DeleteContext(), true),
                new Step(new UpdateContext(), true),
                new Step(new MergeContext(), true),
        };
    }

    public Role canBeExecutedBy() {
        return Role.RepositoryAdmin;
    }


    @Override
    public List<Message> generateMessages(ContextDBRepository contextDBRepository) {
        String parameterId = getAffectedElement();
        List<Message> messages = new LinkedList<>();
        try (Flora2Repository fl = new Flora2Repository()) {
            Context root = fl.getRootContext(contextDBRepository);
            String rootParameterValue = parameter.getParameterValueHierarchy().getName();
            List<Context> childContexts = root.getChildrenFlat();
            for (Context context : childContexts) {
                if (!context.getParameterValues().get(parameterId).equals(rootParameterValue)) {
                    Message m = new Message();
                    m.setTitle("Parameter " + parameterId + " will be deleted.");
                    m.setContent("Please delete Context or update its ParameterValue for \"" + parameterId + "\" to the RootParameterValue \"" + rootParameterValue +  "\".");
                    m.setAffectedElement(context.getName());
                    m.setAffectedElementType("Context");
                    m.setSender(getExecutedBy());
                    m.getRecipients().add(getExecutedBy());
                    m.setTime(getExecutedAt());
                    messages.add(m);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


        return messages;
    }

    @Override
    public boolean onExecuted(ContextDBRepository contextDBRepository) {
        String id = getAffectedElement();
        try (Flora2Repository fl = new Flora2Repository()) {
            boolean result = fl.delParameter(id);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
