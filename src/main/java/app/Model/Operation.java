package app.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Anna on 11.12.2017.
 */
@Entity
public abstract class Operation {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private Date executedAt;

    private boolean isExecuted;

    private String text;

    @ManyToOne
    @JoinColumn(name = "user_id", updatable = false, insertable = false)
    private User executedBy;

    @Column(name = "user_id", updatable = true, insertable = true)
    private Long userId;

    private String affectedElement;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "parent_id", updatable = false, insertable = false)
    private ComposedOperation parent;

    @Column(name = "parent_id", updatable = true, insertable = true)
    private Long parentId;

    public ComposedOperation getParent() {
        return parent;
    }

    public void setParent(ComposedOperation parent) {
        this.parent = parent;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Operation() {
    }

    public Operation(Long parentId, Date executedAt, boolean isExecuted, String text, Long userId, String affectedElement) {
        this.parentId = parentId;
        this.executedAt = executedAt;
        this.isExecuted = isExecuted;
        this.text = text;
        this.userId = userId;
        this.affectedElement = affectedElement;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public abstract String getAbstractType();
    public String getConcreteType() {
        return this.getClass().getSimpleName();
    }
    public abstract String getVerb();
    public abstract String getAffectedElementType();
}
