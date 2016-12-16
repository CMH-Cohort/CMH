package org.wcci.cmh;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {
<<<<<<< HEAD

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String username;
    private String password;

=======
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String username;
	
	
	protected User() {}

	public User(String username){
		this.username = username;
	}

	public long getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}
	
	
	
	
>>>>>>> af239466f659298ae7ce9d0d4a9661325d8fc6fa

}
