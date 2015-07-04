package com.library.management.impl.write.service.member;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.library.management.api.converter.IConverterFactory;
import com.library.management.api.dto.member.MemberDTO;
import com.library.management.api.entity.Author;
import com.library.management.api.entity.Member;
import com.library.management.api.rest.exception.handler.LibraryException;
import com.library.management.api.write.service.member.MemberWriteService;
import com.library.management.impl.repository.member.MemberJpaRepository;

@Service
public class MemberWriteServiceImpl implements MemberWriteService {


	@Resource
	MemberJpaRepository memberJpaRep;

	@Resource 
	IConverterFactory converterFactory;

	@Override
	public Member save(MemberDTO memberDTO) {
		Member member = new Member();
		member = (Member)converterFactory.convertBeans(memberDTO, member);
		member = memberJpaRep.save(member);

		return member;

	}
	

	@Override
	public Member update(Long id, MemberDTO memberDTO) {
		if(memberDTO.getId().longValue() != id.longValue()){
			throw new LibraryException("Cannot updated ID");
		}
		Member originalMember = memberJpaRep.findOne(id);

		Member updatedMember = (Member)converterFactory.convertBeans(memberDTO, originalMember);
						updatedMember = memberJpaRep.save(updatedMember);
		return updatedMember;

	}

	
	@Override
	public String delete(Long id) {
		Member member = memberJpaRep.findOne(id);
		String response = "Success";
		 memberJpaRep.delete(member);
		return response;
	}



	

	
	

}
