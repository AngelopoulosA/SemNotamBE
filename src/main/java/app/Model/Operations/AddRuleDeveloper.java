package app.Model.Operations;

import app.Model.ComposedOperation;
import app.Model.Role;

import javax.persistence.Entity;

@Entity
public class AddRuleDeveloper extends ComposedOperation {

    public Step[] getAllowedOperations() {
        return new Step[] {
        };
    }

    @Override
    public String getVerb() {
        return "Add";
    }

    @Override
    public String getAffectedElementType() {
        return "RuleDeveloper";
    }

    public Role canBeExecutedBy() {
        return Role.RepositoryAdmin;
    }
}
