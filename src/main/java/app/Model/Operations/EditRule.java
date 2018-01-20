package app.Model.Operations;

import app.Model.ComposedOperation;
import javax.persistence.Entity;

@Entity
public class EditRule extends ComposedOperation {

    @Override
    public Step[] getAllowedOperations() {
        return new Step[] {
                new Step(EditRule.class, true),
        };
    }

    @Override
    public String getVerb() {
        return "Edit";
    }

    @Override
    public String getAffectedElementType() {
        return "Rule";
    }
}
