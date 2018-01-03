package app.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.LinkedList;
import java.util.List;

public class ParameterValue {
    private String name;
    private List<ParameterValue> children;
    private List<ParameterValue> parents;

    public ParameterValue(String name) {
        this.setName(name);
        this.setChildren(new LinkedList<>());
        this.setParents(new LinkedList<>());
    }

    public List<ParameterValue> getChildren() {
        return children;
    }

    public void setChildren(List<ParameterValue> children) {
        this.children = children;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonIgnore
    public List<ParameterValue> getParents() {
        return parents;
    }

    public void setParents(List<ParameterValue> parents) {
        this.parents = parents;
    }
}
