package app.Model.Flora2;

public class Rule {
    private String id;
    private String body;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Rule(String id, String body) {
        this.id = id;
        this.body = body;
    }

    public Rule() {
    }
}
