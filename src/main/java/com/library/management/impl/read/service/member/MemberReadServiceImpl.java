package com.library.management.impl.read.service.member;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.management.api.converter.IConverterFactory;
import com.library.management.api.dto.member.MemberDTO;
import com.library.management.api.entity.Member;
import com.library.management.api.read.service.member.MemberReadService;
import com.library.management.api.rest.exception.handler.LibraryException;
import com.library.management.impl.log.Log;
import com.library.management.impl.repository.member.MemberJpaRepository;

@Service
public class MemberReadServiceImpl implements MemberReadService {

	private static final String MEMBER_DOES_NOT_EXIST = "Member does not exist.";

	@Resource
	MemberJpaRepository memberJpaRep;

	@Autowired
	private EntityManagerFactory emf;
	
	@Inject 
	IConverterFactory converterFactory;
	
	@Override
	public MemberDTO findById(Long id)  {
		Member  member= memberJpaRep.findOne(id);
		MemberDTO memberDto = new MemberDTO();
		if(member == null){
			Log.log(Log.ERROR, MEMBER_DOES_NOT_EXIST);

			throw new LibraryException(MEMBER_DOES_NOT_EXIST);
			
			
		}else{
			memberDto = (MemberDTO) converterFactory.convertBeans(member, memberDto);
			
		}
		
		return memberDto;
		
	}

	@Override
	public List<MemberDTO> findAll()  {
		List<Member>  members= memberJpaRep.findAll();
		List<MemberDTO> memberDTOs = new ArrayList<MemberDTO>();
		members.forEach( member-> {
				memberDTOs.add((MemberDTO) converterFactory.convertBeans(member, new MemberDTO()));
		});
		return memberDTOs;

	}

	private boolean isNumeric(String str){
		boolean isNumeric = false;
		try{
		Long.parseLong(str);
		isNumeric = true;
		}catch(NumberFormatException nfe){
			isNumeric = false;
		}
		return isNumeric;
	}


}
