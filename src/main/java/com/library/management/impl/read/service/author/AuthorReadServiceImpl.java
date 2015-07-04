package com.library.management.impl.read.service.author;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.management.api.converter.IConverterFactory;
import com.library.management.api.dto.author.AuthorDTO;
import com.library.management.api.entity.Author;
import com.library.management.api.read.service.author.AuthorReadService;
import com.library.management.api.rest.exception.handler.LibraryException;
import com.library.management.impl.log.Log;
import com.library.management.impl.repository.author.AuthorJpaRepository;

@Service
public class AuthorReadServiceImpl implements AuthorReadService {

	private static final String AUTHOR_DOES_NOT_EXIST = "Author does not exist.";

	@Resource
	AuthorJpaRepository authorJpaRep;

	@Autowired
	private EntityManagerFactory emf;
	
	@Inject 
	IConverterFactory converterFactory;
	
	@Override
	public AuthorDTO findById(Long id)  {
		Author  author= authorJpaRep.findOne(id);
		AuthorDTO authorDto = new AuthorDTO();
		if(author == null){
			Log.log(Log.ERROR, AUTHOR_DOES_NOT_EXIST);

			throw new LibraryException(AUTHOR_DOES_NOT_EXIST);
			
			
		}else{
			authorDto = (AuthorDTO) converterFactory.convertBeans(author, authorDto);
			
		}
		
		return authorDto;
		
	}

	@Override
	public List<AuthorDTO> findAll()  {
		List<Author>  authors= authorJpaRep.findAll();
		List<AuthorDTO> authorDTOs = new ArrayList<AuthorDTO>();
		authors.forEach( author-> {
				authorDTOs.add((AuthorDTO) converterFactory.convertBeans(author, new AuthorDTO()));
		});
		return authorDTOs;

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
