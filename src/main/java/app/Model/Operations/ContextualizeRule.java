package app.Model.Operations;

import app.Model.ComposedOperation;

import javax.persistence.Entity;

@Entity
public class ContextualizeRule extends ComposedOperation {

    public Step[] getAllowedOperations() {
        return new Step[] {
                new Step(EditRule.class, true),
                new Step(AddRule.class, true),
        };
    }

    @Override
    public String getVerb() {
        return "Contextualize";
    }

    @Override
    public String getAffectedElementType() {
        return "Rule";
    }
}
