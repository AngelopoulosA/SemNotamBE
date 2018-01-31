package app.Model.Operations;

import app.Model.ComposedOperation;
import app.Model.Role;

import javax.persistence.Entity;

@Entity
public class DecontextualizeRule extends ComposedOperation {

    public Step[] getAllowedOperations() {
        return new Step[] {
                new Step(new EditRule(), true),
                new Step(new AddRule(), true),
        };
    }

    @Override
    public String getVerb() {
        return "Decontextualize";
    }

    @Override
    public String getAffectedElementType() {
        return "Rule";
    }

    public Role canBeExecutedBy() {
        return Role.RuleDeveloper;
    }
}
