package app.Model.Operations;

import app.Model.ComposedOperation;
import app.Model.Role;

import javax.persistence.Entity;
import java.util.Date;

@Entity
public class DeleteParameterValue extends ComposedOperation {

    public DeleteParameterValue(Long parentId, Date executedAt, boolean isExecuted, String text, Long executedBy, String affectedElement) {
        super(parentId, executedAt, isExecuted, text, executedBy, affectedElement);
    }

    public DeleteParameterValue() {
    }

    @Override
    public String getVerb() {
        return "Delete";
    }

    @Override
    public String getAffectedElementType() {
        return "ParameterValue";
    }

    @Override
    public Step[] getAllowedOperations() {
        return new Step[] {
        };
    }

    public Role canBeExecutedBy() {
        return Role.RepositoryAdmin;
    }
}
