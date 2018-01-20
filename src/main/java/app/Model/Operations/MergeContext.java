package app.Model.Operations;

import app.Model.ComposedOperation;
import javax.persistence.Entity;

@Entity
public class MergeContext extends ComposedOperation {
    @Override
    public Step[] getAllowedOperations() {
        return new Step[] {

        };
    }

    @Override
    public String getVerb() {
        return "Merge";
    }

    @Override
    public String getAffectedElementType() {
        return "Context";
    }
}
