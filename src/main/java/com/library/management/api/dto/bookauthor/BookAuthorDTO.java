package com.library.management.api.dto.bookauthor;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class BookAuthorDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7680671649158125863L;

	private Long id = new Long(-1);

	private Long bookId;

	private Long authorId;
	
	public BookAuthorDTO(){
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getBookId() {
		return bookId;
	}

	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}

	public Long getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Long authorId) {
		this.authorId = authorId;
	}

	}
