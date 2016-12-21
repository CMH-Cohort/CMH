package org.wcci.cmh;

import javax.persistence.*;

@Entity
public class TermStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private boolean done;

    @OneToOne
    private Term term;

    protected TermStatus() {

    }

    public TermStatus(Term term, boolean done) {
        this.term = term;
        this.done = done;
    }

    public Term getTerm() {
        return term;
    }

    public long getId() {
        return id;
    }

    public boolean isDone() {
        return done;
    }

    public void markDone() {
        done = true;
    }

	public void markNotDone() {
		done = false;
	}


}
