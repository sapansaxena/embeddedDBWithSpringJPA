package com.library.management.impl.read.service.membercopy;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.management.api.converter.IConverterFactory;
import com.library.management.api.dto.membercopy.MemberCopyDTO;
import com.library.management.api.entity.MemberCopy;
import com.library.management.api.read.service.membercopy.MemberCopyReadService;
import com.library.management.api.rest.exception.handler.LibraryException;
import com.library.management.impl.log.Log;
import com.library.management.impl.repository.membercopy.MemberCopyJpaRepository;

@Service
public class MemberCopyReadServiceImpl implements MemberCopyReadService {

	private static final String BOOK_DOES_NOT_EXIST = "Book does not exist.";

	@Resource
	MemberCopyJpaRepository memberCopyJpaRep;

	@Autowired
	private EntityManagerFactory emf;
	
	@Inject 
	IConverterFactory converterFactory;
	
	@Override
	public MemberCopyDTO findById(Long id)  {
		MemberCopy  memberCopy= memberCopyJpaRep.findOne(id);
		MemberCopyDTO memberCopyDto = new MemberCopyDTO();
		if(memberCopy == null){
			Log.log(Log.ERROR, BOOK_DOES_NOT_EXIST);

			throw new LibraryException(BOOK_DOES_NOT_EXIST);
			
			
		}else{
			memberCopyDto = (MemberCopyDTO) converterFactory.convertBeans(memberCopy, memberCopyDto);
			
		}
		
		return memberCopyDto;
		
	}

	@Override
	public List<MemberCopyDTO> findAll()  {
		List<MemberCopy>  memberCopys= memberCopyJpaRep.findAll();
		List<MemberCopyDTO> memberCopyDTOs = new ArrayList<MemberCopyDTO>();
		memberCopys.forEach( memberCopy-> {
				memberCopyDTOs.add((MemberCopyDTO) converterFactory.convertBeans(memberCopy, new MemberCopyDTO()));
		});
		return memberCopyDTOs;

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
