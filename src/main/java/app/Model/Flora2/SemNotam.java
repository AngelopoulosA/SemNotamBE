package app.Model.Flora2;

import app.Model.Flora2.Notam;

import java.util.List;

public class SemNotam {
    private Notam notam;
    private List<String> results;

    public List<String> getResults() {
        return results;
    }

    public void setResults(List<String> results) {
        this.results = results;
    }

    public Notam getNotam() {
        return notam;
    }

    public void setNotam(Notam notam) {
        this.notam = notam;
    }

    public SemNotam(Notam notam, List<String> results) {
        this.notam = notam;
        this.results = results;
    }
}
