package app.Model;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class TempParameterValue {
    private String name;
    private List<TempParameterValue> children;

    public List<TempParameterValue> getParents() {
        return parents;
    }

    public void setParents(List<TempParameterValue> parents) {
        this.parents = parents;
    }

    private List<TempParameterValue> parents;

    public TempParameterValue(String name) {
        this.setName(name);
        this.setChildren(new LinkedList<>());
        this.setParents(new LinkedList<>());
    }

    public List<TempParameterValue> getChildren() {
        return children;
    }

    public void setChildren(List<TempParameterValue> children) {
        this.children = children;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ParameterValue toParameterValue() {
        ParameterValue pv = new ParameterValue(name);
        pv.setChildren(children.stream().filter(c -> c.getParents().get(0) == this).map(c -> c.toParameterValue()).collect(Collectors.toList()));
        return pv;
    }
}
