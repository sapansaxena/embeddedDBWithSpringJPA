package com.library.management.api.read.service.membercopy;


import java.util.List;

import com.library.management.api.dto.membercopy.MemberCopyDTO;

public interface MemberCopyReadService {
	
	 MemberCopyDTO findById(Long id);
	 
	 List<MemberCopyDTO> findAll();
	 

}
