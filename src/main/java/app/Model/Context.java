package app.Model;

import java.util.LinkedList;
import java.util.List;

public class Context {
    private String name;
    private List<Context> children;

    public Context(String name) {
        this.setName(name);
        this.setChildren(new LinkedList<>());
    }

    public List<Context> getChildren() {
        return children;
    }

    public void setChildren(List<Context> children) {
        this.children = children;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
