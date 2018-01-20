package app.Model.Flora2;

import java.util.HashMap;
import java.util.Map;

public class Notam {
    private String name;
    private Map<String, String> properties;

    public Notam(String name) {
        this.name = name;
        this.properties = new HashMap<>();
    }

    public Map<String, String> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, String> properties) {
        this.properties = properties;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
