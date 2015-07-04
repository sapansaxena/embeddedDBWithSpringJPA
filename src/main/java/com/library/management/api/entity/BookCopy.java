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
@NamedQuery(name="BookCopy.findAll", query="SELECT b FROM BookCopy b")
@Table(name="book_copy")

public class BookCopy implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="BOOK_COPY_ID_GENERATOR", sequenceName="BOOK_COPY_SEQUENCE", allocationSize=1, initialValue=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="BOOK_COPY_ID_GENERATOR")
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

	@Column(name="is_borrowed")
	private Boolean isBorrowed;
	
	@Column(name="borrow_count")
	private Long borrowCount;
	
	@OneToOne
	@JoinColumn(name="book_id", insertable=false, updatable= false )
	private Book book;


	public BookCopy() {
	}


	public Long getBookId() {
		return bookId;
	}


	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}


	public Boolean getIsBorrowed() {
		return isBorrowed;
	}


	public void setIsBorrowed(Boolean isBorrowed) {
		this.isBorrowed = isBorrowed;
	}


	public Long getBorrowCount() {
		return borrowCount;
	}


	public void setBorrowCount(Long borrowCount) {
		this.borrowCount = borrowCount;
	}


	public Book getBook() {
		return book;
	}


	public void setBook(Book book) {
		this.book = book;
	}



}
