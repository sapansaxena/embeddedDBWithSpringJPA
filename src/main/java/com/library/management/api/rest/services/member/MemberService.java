package com.library.management.api.rest.services.member;

import javax.validation.Valid;

import com.library.management.api.dto.member.MemberDTO;



public interface  MemberService {	
	
		public  MemberDTO getMember(Long  custId);
		

	    public MemberDTO createMember(@Valid MemberDTO memberDTO);
	 	
	 	
	    public MemberDTO updateMember( Long  id, @Valid MemberDTO memberDTO);
	 	
	 	
	    public String deleteMember( Long id);


	
}
