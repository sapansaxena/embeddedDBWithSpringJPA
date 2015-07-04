package com.library.management.api.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * The persistent class for the book database table.
 * 
 */
@Entity
@NamedQuery(name="BookAuthor.findAll", query="SELECT b FROM BookAuthor b")
@Table(name="book_author")

public class BookAuthor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="BOOK_AUTHOR_ID_GENERATOR", sequenceName="BOOK_AUTHOR_SEQUENCE", allocationSize=1, initialValue=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="BOOK_AUTHOR_ID_GENERATOR")
	@Column(insertable=false, updatable=false, unique=true, nullable=false)
	private Long id;
	
	


	public Long getId() {
		return id;
	}


	public void setId(Long _id) {
		this.id = _id;
	}

	
	@Column(name="book_id")
	private Long bookId;

	@Column(name="author_id")
	private Long authorId;
	
	@OneToOne
	@JoinColumn(name="author_id", insertable=false, updatable= false )
	private Author author;
	
	@OneToOne
	@JoinColumn(name="book_id", insertable=false, updatable= false )
	private Book book;


	public BookAuthor() {
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


	public Author getAuthor() {
		return author;
	}


	public void setAuthor(Author author) {
		this.author = author;
	}


	public Book getBook() {
		return book;
	}


	public void setBook(Book book) {
		this.book = book;
	}
	
	
	
	
}
