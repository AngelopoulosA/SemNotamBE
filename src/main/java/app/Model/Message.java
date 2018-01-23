package app.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private Date time;
    private String title;
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User sender;

    @ManyToMany
    @JoinTable()
    private List<User> recipients;
    private String type;
    private String affectedElement;
    private String affectedElementType;
    private boolean isAcknowledged;
    private boolean isRead;

    @JsonIgnore
    @OneToOne(mappedBy = "message")
    private SendMessage sendMessageOperation;


    @JsonIgnore
    @OneToOne(mappedBy = "message")
    private AcknowledgeMessage acknowledgeMessageOperation;

    public Message(){
        this.recipients = new LinkedList<>();
    }

    public Message(String title, String content, User sender){
        this.title = title;
        this.content = content;
        this.sender = sender;
    }

    public Message(String title, String content, User sender, List<User> recipients, String affectedElement, String affectedElementType) {
        this.title = title;
        this.content = content;
        this.sender = sender;
        this.recipients = recipients;
        this.affectedElement = affectedElement;
        this.affectedElementType = affectedElementType;
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public List<User> getRecipients() {
        return recipients;
    }

    public void setRecipients(List<User> recipients) {
        this.recipients = recipients;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAffectedElement() {
        return affectedElement;
    }

    public void setAffectedElement(String affectedElement) {
        this.affectedElement = affectedElement;
    }

    public boolean isAcknowledged() {
        return isAcknowledged;
    }

    public void setAcknowledged(boolean acknowledged) {
        isAcknowledged = acknowledged;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    public SendMessage getSendMessageOperation() {
        return sendMessageOperation;
    }

    public void setSendMessageOperation(SendMessage sendMessageOperation) {
        this.sendMessageOperation = sendMessageOperation;
    }

    public String getAffectedElementType() {
        return affectedElementType;
    }

    public void setAffectedElementType(String affectedElementType) {
        this.affectedElementType = affectedElementType;
    }

    public AcknowledgeMessage getAcknowledgeMessageOperation() {
        return acknowledgeMessageOperation;
    }

    public void setAcknowledgeMessageOperation(AcknowledgeMessage acknowledgeMessageOperation) {
        this.acknowledgeMessageOperation = acknowledgeMessageOperation;
    }
}
