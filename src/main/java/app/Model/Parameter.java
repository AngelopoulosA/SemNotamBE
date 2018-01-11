package app.Model;

import java.util.LinkedList;
import java.util.List;

public class Parameter {
    private String name;
    private ParameterValue parameterValueHierarchy;
    private List<String> detParamValue;
    private List<ParameterValue> parameterValues;

    public List<ParameterValue> getParameterValues() {
        return parameterValues;
    }

    public void setParameterValues(List<ParameterValue> parameterValues) {
        this.parameterValues = parameterValues;
    }

    public Parameter() {
        detParamValue = new LinkedList<>();
        parameterValues = new LinkedList<>();
    }

    public Parameter(String name, List<String> parameterValues) {
        this.name = name;
    }

    public Parameter(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ParameterValue getParameterValueHierarchy() {
        return parameterValueHierarchy;
    }

    public void setParameterValueHierarchy(ParameterValue parameterValueHierarchy) {
        this.parameterValueHierarchy = parameterValueHierarchy;
    }

    public List<String> getDetParamValue() {
        return detParamValue;
    }

    public void setDetParamValue(List<String> detParamValue) {
        this.detParamValue = detParamValue;
    }
}
