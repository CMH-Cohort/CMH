package org.wcci.cmh;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class User {

	@OneToMany(targetEntity = TermStatus.class, cascade = CascadeType.ALL) 
	private List<TermStatus> termStatuses;
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String username;
    private String password;

    protected User() {
    }

    public User(String username) {
        this.username = username;
    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public List<TermStatus> getTermStatuses() {
    	return this.termStatuses;
    }

}
