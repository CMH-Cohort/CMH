package org.wcci.cmh;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Term {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull //makes title required to enter from user, w/ Anthony
    private String title;

    @OneToOne(mappedBy = "term", cascade = CascadeType.ALL)
    private TermStatus termStatus;

    protected Term() {
    }

    public Term(String title) {
        this.title = title;
        termStatus = new TermStatus(this, false);
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "Term [title=" + title + "]";
    }

    public TermStatus getStatus() {
        return termStatus;
    }

}
