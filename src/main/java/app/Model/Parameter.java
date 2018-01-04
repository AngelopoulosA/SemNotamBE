package app.Model;

import java.util.List;

public class Parameter {
    private String name;
    private ParameterValue parameterValueHierarchy;
    private List<String> detParamValue;

    public Parameter() {

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
