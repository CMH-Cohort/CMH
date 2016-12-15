package org.wcci.cmh;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Term {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@NotNull //makes title required to enter from user, w/ Anthony
	private String title;

	protected Term() {}

	public Term(String title){
		this.title = title;
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

}
