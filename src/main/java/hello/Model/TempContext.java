package hello.Model;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class TempContext {
    private String name;
    private List<TempContext> children;

    public List<TempContext> getParents() {
        return parents;
    }

    public void setParents(List<TempContext> parents) {
        this.parents = parents;
    }

    private List<TempContext> parents;

    public TempContext(String name) {
        this.setName(name);
        this.setChildren(new LinkedList<>());
        this.setParents(new LinkedList<>());
    }

    public List<TempContext> getChildren() {
        return children;
    }

    public void setChildren(List<TempContext> children) {
        this.children = children;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Context toContext() {
        Context pv = new Context(name);
        pv.setChildren(children.stream().filter(c -> c.getParents().get(0) == this).map(c -> c.toContext()).collect(Collectors.toList()));
        return pv;
    }
}
