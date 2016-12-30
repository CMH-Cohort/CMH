package org.wcci.cmh;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
public class Term {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull //makes title required to enter from user, w/ Anthony
    private String title;

    @OneToMany(mappedBy = "term", orphanRemoval = true)
    @Cascade({CascadeType.ALL})
    private List<TermStatus> termStatuses;

    protected Term() {
    }

    public Term(String title) {
        this.title = title;
        termStatuses = new ArrayList<TermStatus>();
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

    public List<TermStatus> getTermStatuses() {
        return termStatuses;
    }
    
    public void setTermStatuses(List<TermStatus> termStatuses){
    	this.termStatuses = termStatuses;
    }

}
