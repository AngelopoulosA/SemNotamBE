package app.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.*;

public class Context {
    private String name;
    private List<Context> children;
    private List<Context> parents;
    private Map<String, String> parameterValues;
    private Map<String, String> rules;
    private List<String> ruleDevelopers;

    public Context() {
        this.setChildren(new LinkedList<>());
        this.setParents(new LinkedList<>());
        this.setParameterValues(new Hashtable<>());
        this.setRules(new Hashtable<>());
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

    public Map<String, String> getRules() {
        return rules;
    }

    public void setRules(Map<String, String> rules) {
        this.rules = rules;
    }

    public List<String> getRuleDevelopers() {
        return ruleDevelopers;
    }

    public void setRuleDevelopers(List<String> ruleDevelopers) {
        this.ruleDevelopers = ruleDevelopers;
    }
}
