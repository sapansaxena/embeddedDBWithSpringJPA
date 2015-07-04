package com.library.management.api.entity;

import java.io.Serializable;
import java.util.Date;

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
@NamedQuery(name="MemberCopy.findAll", query="SELECT b FROM MemberCopy b")
@Table(name="member_copy")

public class MemberCopy implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="MEMBER_COPY_ID_GENERATOR", sequenceName="MEMBER_COPY_SEQUENCE", allocationSize=1, initialValue=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MEMBER_COPY_ID_GENERATOR")
	@Column(insertable=false, updatable=false, unique=true, nullable=false)
	private Long id;
	
	


	public Long getId() {
		return id;
	}


	public void setId(Long _id) {
		this.id = _id;
	}

	
	@Column(name="book_copy_id")
	private Long bookCopyId;

	@Column(name="member_id")
	private Long memberId;
	
	@OneToOne
	@JoinColumn(name="member_id", insertable=false, updatable= false )
	private Member member;
	
	@OneToOne
	@JoinColumn(name="book_copy_id", insertable=false, updatable= false )
	private BookCopy bookCopy;
	
	@Column(name="date_of_borrow")
private Date dateOfBorrow;	


	public MemberCopy() {
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


	public Member getMember() {
		return member;
	}


	public void setMember(Member member) {
		this.member = member;
	}


	public BookCopy getBookCopy() {
		return bookCopy;
	}


	public void setBookCopy(BookCopy bookCopy) {
		this.bookCopy = bookCopy;
	}


	public Date getDateOfBorrow() {
		return dateOfBorrow;
	}


	public void setDateOfBorrow(Date dateOfBorrow) {
		this.dateOfBorrow = dateOfBorrow;
	}

}
