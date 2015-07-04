package com.library.management.api.dto.book;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class BookDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7680671649158125863L;

	private Long id = new Long(-1);

	@NotNull()
	@Size(min=1, max=255)
	private String title;

	@NotNull()
	@Size(max = 255, min=1)
	private String publisher;
	
	@Size(max=255)
	@NotNull()
	private String isbn;
	
	public BookDTO(){
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	
	
}
