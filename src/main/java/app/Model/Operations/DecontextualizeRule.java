package app.Model.Operations;

import app.Model.ComposedOperation;

import javax.persistence.Entity;

@Entity
public class DecontextualizeRule extends ComposedOperation {

    public Step[] getAllowedOperations() {
        return new Step[] {
                new Step(EditRule.class, true),
                new Step(AddRule.class, true),
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
}
