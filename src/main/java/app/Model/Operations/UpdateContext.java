package app.Model.Operations;

import app.Model.ComposedOperation;
import app.Model.Role;

import javax.persistence.Entity;
import java.util.Date;

@Entity
public class UpdateContext extends ComposedOperation {


    public UpdateContext() {
    }

    @Override
    public String getVerb() {
        return "Update";
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

    public Role canBeExecutedBy() {
        return Role.RepositoryAdmin;
    }
}
