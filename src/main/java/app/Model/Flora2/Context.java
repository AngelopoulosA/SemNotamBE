package app.Model.Flora2;

import app.Model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.*;

public class Context {
    private String name;
    private List<Context> children;
    private List<Context> parents;
    private Map<String, String> parameterValues;
    private List<Rule>  rules;
    private List<User> ruleDevelopers;

    public Context() {
        this.setChildren(new LinkedList<>());
        this.setParents(new LinkedList<>());
        this.setParameterValues(new Hashtable<>());
        this.setRules(new LinkedList<>());
        this.setRuleDevelopers(new LinkedList<>());
    }

    public Context(String name) {
        this();
        this.setName(name);
    }

    public List<Context> getChildren() {
        return children;
    }

    public void setChildren(List<Context> children) {
        this.children = children;
    }

    @JsonIgnore
    public List<Context> getParents() {
        return parents;
    }

    public void setParents(List<Context> parents) {
        this.parents = parents;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, String> getParameterValues() {
        return parameterValues;
    }

    public void setParameterValues(Map<String, String> parameterValues) {
        this.parameterValues = parameterValues;
    }

    public List<Rule>  getRules() {
        return rules;
    }

    public void setRules(List<Rule> rules) {
        this.rules = rules;
    }

    public List<User> getRuleDevelopers() {
        return ruleDevelopers;
    }

    public void setRuleDevelopers(List<User> ruleDevelopers) {
        this.ruleDevelopers = ruleDevelopers;
    }

    @JsonIgnore
    public List<Context> getChildrenFlat() {
        List<Context> childrenFlat = new LinkedList<>();
        for (Context c : children) {
            childrenFlat.addAll(c.getChildrenFlat());
            childrenFlat.add(c);
        }
        return childrenFlat;
    }

    @JsonIgnore
    public List<Context> getParentsFlat() {
        List<Context> parentsFlat = new LinkedList<>();
        for (Context c : parents) {
            parentsFlat.addAll(c.getParentsFlat());
            parentsFlat.add(c);
        }
        return parentsFlat;
    }

    @JsonIgnore
    public Context findContext(String id) {
        if (getName().equals(id)) {
            return this;
        }

        for (Context c : children) {
            Context found = c.findContext(id);
            if (found != null) {
                return found;
            }
        }
        return null;
    }
}
