package com.library.management.api.write.service.member;

import com.library.management.api.dto.member.MemberDTO;
import com.library.management.api.entity.Member;

public interface  MemberWriteService {
	
	public Member save(MemberDTO member);
	public String delete(Long id);
	public Member update(Long id, MemberDTO memberDTO);


}
