package com.library.management.api.dto.bookcopy;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class BookCopyDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7680671649158125863L;

	private Long id = new Long(-1);

	private Long bookId;

	private Boolean isBorrowed;
	
	private Long borrowCount;
	
	public BookCopyDTO(){
		
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


	}
