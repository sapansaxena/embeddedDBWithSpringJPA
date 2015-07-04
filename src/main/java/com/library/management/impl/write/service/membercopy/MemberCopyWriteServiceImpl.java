package com.library.management.impl.write.service.membercopy;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.library.management.api.converter.IConverterFactory;
import com.library.management.api.dto.membercopy.MemberCopyDTO;
import com.library.management.api.entity.MemberCopy;
import com.library.management.api.rest.exception.handler.LibraryException;
import com.library.management.api.write.service.membercopy.MemberCopyWriteService;
import com.library.management.impl.repository.membercopy.MemberCopyJpaRepository;

@Service
public class MemberCopyWriteServiceImpl implements MemberCopyWriteService {


	@Resource
	MemberCopyJpaRepository memberCopyJpaRep;

	@Resource 
	IConverterFactory converterFactory;

	@Override
	public MemberCopy save(MemberCopyDTO memberCopyDTO) {
		MemberCopy memberCopy = new MemberCopy();
		memberCopy = (MemberCopy)converterFactory.convertBeans(memberCopyDTO, memberCopy);
		memberCopy = memberCopyJpaRep.save(memberCopy);

		return memberCopy;

	}
	

	@Override
	public MemberCopy update(Long id, MemberCopyDTO memberCopyDTO) {
		if(memberCopyDTO.getId().longValue() != id.longValue()){
			throw new LibraryException("Cannot updated ID");
		}
		MemberCopy originalMemberCopy = memberCopyJpaRep.findOne(id);

		MemberCopy updatedMemberCopy = (MemberCopy)converterFactory.convertBeans(memberCopyDTO, originalMemberCopy);
						updatedMemberCopy = memberCopyJpaRep.save(updatedMemberCopy);
		return updatedMemberCopy;

	}

	
	@Override
	public String delete(Long id) {
		MemberCopy memberCopy = memberCopyJpaRep.findOne(id);
		String response = "Success";
		 memberCopyJpaRep.delete(memberCopy);
		return response;
	}



	

	
	

}
