package app.Model.Operations;

import app.Model.ComposedOperation;

import javax.persistence.Entity;

@Entity
public class AddRuleDeveloper extends ComposedOperation {

    public Step[] getAllowedOperations() {
        return new Step[] {
                new Step(EditRule.class, true),
                new Step(DeleteRule.class, true),
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
}
