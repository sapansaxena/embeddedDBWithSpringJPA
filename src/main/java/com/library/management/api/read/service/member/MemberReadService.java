package com.library.management.api.read.service.member;


import java.util.List;

import com.library.management.api.dto.author.AuthorDTO;
import com.library.management.api.dto.member.MemberDTO;

public interface MemberReadService {
	
	 MemberDTO findById(Long id);
	 
	 List<MemberDTO> findAll();
	 

}
