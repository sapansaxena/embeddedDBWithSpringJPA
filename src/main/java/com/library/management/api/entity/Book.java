package com.library.management.api.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;


/**
 * The persistent class for the book database table.
 * 
 */
@Entity
@NamedQuery(name="Book.findAll", query="SELECT b FROM Book b")

public class Book implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="BOOK_ID_GENERATOR", sequenceName="BOOK_SEQUENCE", allocationSize=1, initialValue=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="BOOK_ID_GENERATOR")
	@Column(insertable=false, updatable=false, unique=true, nullable=false)
	private Long id;
	
	


	public Long getId() {
		return id;
	}


	public void setId(Long _id) {
		this.id = _id;
	}

	
	@Column(name="title")
	private String title;

	@Column(name="publisher")
	@NotBlank
	@Length(max=255)
	private String publisher;

	@Column(name="isbn")
	@NotBlank
	@Length(max=255)
	private String isbn;
	
	public Book() {
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
