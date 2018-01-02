package app.Model;

import java.util.LinkedList;
import java.util.List;

public class ParameterValue {
    private String name;
    private List<ParameterValue> children;

    public ParameterValue(String name) {
        this.setName(name);
        this.setChildren(new LinkedList<>());
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
}
