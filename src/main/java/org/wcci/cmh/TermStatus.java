package org.wcci.cmh;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class TermStatus {
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    private boolean done;
	
	@OneToOne
	Term term;
		
	protected TermStatus() {}
	
	public TermStatus (boolean done) {
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
	
	

}
