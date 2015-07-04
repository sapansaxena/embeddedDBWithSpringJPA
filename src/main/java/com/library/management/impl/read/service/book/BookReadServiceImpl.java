package com.library.management.impl.read.service.book;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.management.api.converter.IConverterFactory;
import com.library.management.api.dto.book.BookDTO;
import com.library.management.api.entity.Book;
import com.library.management.api.read.service.book.BookReadService;
import com.library.management.api.rest.exception.handler.LibraryException;
import com.library.management.impl.log.Log;
import com.library.management.impl.repository.book.BookJpaRepository;

@Service
public class BookReadServiceImpl implements BookReadService {

	private static final String BOOK_DOES_NOT_EXIST = "Book does not exist.";

	@Resource
	BookJpaRepository bookJpaRep;

	@Autowired
	private EntityManagerFactory emf;
	
	@Inject 
	IConverterFactory converterFactory;
	
	@Override
	public BookDTO findById(Long id)  {
		Book  book= bookJpaRep.findOne(id);
		BookDTO bookDto = new BookDTO();
		if(book == null){
			Log.log(Log.ERROR, BOOK_DOES_NOT_EXIST);

			throw new LibraryException(BOOK_DOES_NOT_EXIST);
			
			
		}else{
			bookDto = (BookDTO) converterFactory.convertBeans(book, bookDto);
			
		}
		
		return bookDto;
		
	}

	@Override
	public List<BookDTO> findAll()  {
		List<Book>  books= bookJpaRep.findAll();
		List<BookDTO> bookDTOs = new ArrayList<BookDTO>();
		books.forEach( book-> {
				bookDTOs.add((BookDTO) converterFactory.convertBeans(book, new BookDTO()));
		});
		return bookDTOs;

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
