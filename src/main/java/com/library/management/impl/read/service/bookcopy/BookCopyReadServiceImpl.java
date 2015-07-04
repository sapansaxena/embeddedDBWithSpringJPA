package com.library.management.impl.read.service.bookcopy;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.management.api.converter.IConverterFactory;
import com.library.management.api.dto.bookcopy.BookCopyDTO;
import com.library.management.api.entity.BookCopy;
import com.library.management.api.read.service.bookcopy.BookCopyReadService;
import com.library.management.api.rest.exception.handler.LibraryException;
import com.library.management.impl.log.Log;
import com.library.management.impl.repository.bookcopy.BookCopyJpaRepository;

@Service
public class BookCopyReadServiceImpl implements BookCopyReadService {

	private static final String BOOK_DOES_NOT_EXIST = "Book does not exist.";

	@Resource
	BookCopyJpaRepository bookCopyJpaRep;

	@Autowired
	private EntityManagerFactory emf;
	
	@Inject 
	IConverterFactory converterFactory;
	
	@Override
	public BookCopyDTO findById(Long id)  {
		BookCopy  bookCopy= bookCopyJpaRep.findOne(id);
		BookCopyDTO bookCopyDto = new BookCopyDTO();
		if(bookCopy == null){
			Log.log(Log.ERROR, BOOK_DOES_NOT_EXIST);

			throw new LibraryException(BOOK_DOES_NOT_EXIST);
			
			
		}else{
			bookCopyDto = (BookCopyDTO) converterFactory.convertBeans(bookCopy, bookCopyDto);
			
		}
		
		return bookCopyDto;
		
	}

	@Override
	public List<BookCopyDTO> findAll()  {
		List<BookCopy>  bookCopys= bookCopyJpaRep.findAll();
		List<BookCopyDTO> bookCopyDTOs = new ArrayList<BookCopyDTO>();
		bookCopys.forEach( bookCopy-> {
				bookCopyDTOs.add((BookCopyDTO) converterFactory.convertBeans(bookCopy, new BookCopyDTO()));
		});
		return bookCopyDTOs;

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
