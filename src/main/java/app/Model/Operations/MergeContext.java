package app.Model.Operations;

import app.Model.ComposedOperation;
import app.Model.Flora2.Context;

import javax.persistence.Entity;
import javax.persistence.Transient;

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
