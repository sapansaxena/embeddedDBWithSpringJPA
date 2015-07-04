package com.library.management.impl.read.service.bookauthor;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.management.api.converter.IConverterFactory;
import com.library.management.api.dto.bookauthor.BookAuthorDTO;
import com.library.management.api.entity.BookAuthor;
import com.library.management.api.read.service.bookauthor.BookAuthorReadService;
import com.library.management.api.rest.exception.handler.LibraryException;
import com.library.management.impl.log.Log;
import com.library.management.impl.repository.bookauthor.BookAuthorJpaRepository;

@Service
public class BookAuthorReadServiceImpl implements BookAuthorReadService {

	private static final String BOOK_DOES_NOT_EXIST = "Book does not exist.";

	@Resource
	BookAuthorJpaRepository bookAuthorJpaRep;

	@Autowired
	private EntityManagerFactory emf;
	
	@Inject 
	IConverterFactory converterFactory;
	
	@Override
	public BookAuthorDTO findById(Long id)  {
		BookAuthor  bookAuthor= bookAuthorJpaRep.findOne(id);
		BookAuthorDTO bookAuthorDto = new BookAuthorDTO();
		if(bookAuthor == null){
			Log.log(Log.ERROR, BOOK_DOES_NOT_EXIST);

			throw new LibraryException(BOOK_DOES_NOT_EXIST);
			
			
		}else{
			bookAuthorDto = (BookAuthorDTO) converterFactory.convertBeans(bookAuthor, bookAuthorDto);
			
		}
		
		return bookAuthorDto;
		
	}

	@Override
	public List<BookAuthorDTO> findAll()  {
		List<BookAuthor>  bookAuthors= bookAuthorJpaRep.findAll();
		List<BookAuthorDTO> bookAuthorDTOs = new ArrayList<BookAuthorDTO>();
		bookAuthors.forEach( bookAuthor-> {
				bookAuthorDTOs.add((BookAuthorDTO) converterFactory.convertBeans(bookAuthor, new BookAuthorDTO()));
		});
		return bookAuthorDTOs;

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
