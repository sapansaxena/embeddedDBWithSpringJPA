package com.library.management.impl.write.service.author;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.library.management.api.converter.IConverterFactory;
import com.library.management.api.dto.author.AuthorDTO;
import com.library.management.api.entity.Author;
import com.library.management.api.rest.exception.handler.LibraryException;
import com.library.management.api.write.service.author.AuthorWriteService;
import com.library.management.impl.log.Log;
import com.library.management.impl.repository.author.AuthorJpaRepository;

@Service
public class AuthorWriteServiceImpl implements AuthorWriteService {


	@Resource
	AuthorJpaRepository authorJpaRep;

	@Resource 
	IConverterFactory converterFactory;

	@Override
	public Author save(AuthorDTO authorDTO) {
		Author author = new Author();
		author = (Author)converterFactory.convertBeans(authorDTO, author);
		author = authorJpaRep.save(author);

		return author;

	}
	

	@Override
	public Author update(Long id, AuthorDTO authorDTO) {
		if(authorDTO.getId().longValue() != id.longValue()){
			throw new LibraryException("Cannot updated ID");
		}
		Author originalAuthor = authorJpaRep.findOne(id);
		Author updatedAuthor = (Author) converterFactory.convertBeans(authorDTO, originalAuthor);

						updatedAuthor = authorJpaRep.save(updatedAuthor);
		return updatedAuthor;

	}

	
	@Override
	public String delete(Long id) {
		Author author = authorJpaRep.findOne(id);
		String response = "Success";
		 authorJpaRep.delete(author);
		return response;
	}



	

	
	

}
