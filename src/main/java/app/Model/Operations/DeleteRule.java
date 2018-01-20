package app.Model.Operations;

import app.Model.ComposedOperation;
import javax.persistence.Entity;

@Entity
public class DeleteRule extends ComposedOperation {

    public Step[] getAllowedOperations() {
        return new Step[] {
                new Step(EditRule.class, true),
                new Step(AddRule.class, true),
        };
    }

    @Override
    public String getVerb() {
        return "Delete";
    }

    @Override
    public String getAffectedElementType() {
        return "Rule";
    }
}
