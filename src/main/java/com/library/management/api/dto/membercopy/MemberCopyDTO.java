package com.library.management.api.dto.membercopy;

import java.io.Serializable;
import java.util.Date;

public class MemberCopyDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7680671649158125863L;

	private Long id = new Long(-1);

	private Long bookCopyId;
	
	private Long memberId;

	private Date dateOfBorrow;
	
	public MemberCopyDTO(){
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getBookCopyId() {
		return bookCopyId;
	}

	public void setBookCopyId(Long bookCopyId) {
		this.bookCopyId = bookCopyId;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public Date getDateOfBorrow() {
		return dateOfBorrow;
	}

	public void setDateOfBorrow(Date dateOfBorrow) {
		this.dateOfBorrow = dateOfBorrow;
	}


	}
