package app.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class AtomicOperation {

    @Id
    private long id;
    private String type;
}
