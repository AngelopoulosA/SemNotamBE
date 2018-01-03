package app.Model;


import javax.persistence.*;
import java.util.Date;

/**
 * Created by Anna on 11.12.2017.
 */
@Entity
public class Operation {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private Date executedAt;

    private boolean isExecuted;

    private String text;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User executedBy;

    private String affectedElement;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getExecutedAt() {
        return executedAt;
    }

    public void setExecutedAt(Date executedAt) {
        this.executedAt = executedAt;
    }

    public boolean isExecuted() {
        return isExecuted;
    }

    public void setExecuted(boolean executed) {
        isExecuted = executed;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getExecutedBy() {
        return executedBy;
    }

    public void setExecutedBy(User executedBy) {
        this.executedBy = executedBy;
    }

    public String getAffectedElement() {
        return affectedElement;
    }

    public void setAffectedElement(String affectedElement) {
        this.affectedElement = affectedElement;
    }
}