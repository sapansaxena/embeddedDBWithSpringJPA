package com.library.management.api.rest.services.membercopy;

import javax.validation.Valid;

import com.library.management.api.dto.membercopy.MemberCopyDTO;



public interface  MemberCopyService {	
	
		public  MemberCopyDTO getMemberCopy(Long  custId);
		

	    public MemberCopyDTO createMemberCopy(@Valid MemberCopyDTO memberCopyDTO);
	 	
	 	
	    public MemberCopyDTO updateMemberCopy( Long  id, @Valid MemberCopyDTO memberCopyDTO);
	 	
	 	
	    public String deleteMemberCopy( Long id);


	
}
