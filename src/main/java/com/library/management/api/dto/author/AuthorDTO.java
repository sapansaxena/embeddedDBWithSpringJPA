package com.library.management.api.dto.author;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class AuthorDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7680671649158125863L;

	private Long id = new Long(-1);

	@NotNull()
	@Size(min=1, max=255)
	private String fname;

	@NotNull()
	@Size(max = 255, min=1)
	private String lname;
	
	@Size(max=255)
	private String email;
	
	public AuthorDTO(){
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
