package app.Model.Operations;

import app.Model.ComposedOperation;
import app.Model.Role;

import javax.persistence.Entity;

@Entity
public class DeleteRuleDeveloper extends ComposedOperation {

    public Step[] getAllowedOperations() {
        return new Step[] {
                new Step(new EditRule(), true),
                new Step(new AddRule(), true),
        };
    }

    @Override
    public String getVerb() {
        return "Delete";
    }

    @Override
    public String getAffectedElementType() {
        return "RuleDeveloper";
    }

    public Role canBeExecutedBy() {
        return Role.RepositoryAdmin;
    }
}
