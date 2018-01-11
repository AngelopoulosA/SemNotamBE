package app.Model;

import javax.persistence.*;
import java.util.List;

@Entity
public class ContextDB {

    @Id
    String name;

    @ManyToMany
    @JoinTable()
    List<User> ruleDevelopers;

    public ContextDB() {
    }

    public ContextDB(String name, List<User> ruleDevelopers) {
        this.name = name;
        this.ruleDevelopers = ruleDevelopers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getRuleDevelopers() {
        return ruleDevelopers;
    }

    public void setRuleDevelopers(List<User> ruleDevelopers) {
        this.ruleDevelopers = ruleDevelopers;
    }
}
