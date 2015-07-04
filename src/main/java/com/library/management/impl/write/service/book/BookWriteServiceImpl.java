package com.library.management.impl.write.service.book;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.library.management.api.converter.IConverterFactory;
import com.library.management.api.dto.book.BookDTO;
import com.library.management.api.entity.Author;
import com.library.management.api.entity.Book;
import com.library.management.api.rest.exception.handler.LibraryException;
import com.library.management.api.write.service.book.BookWriteService;
import com.library.management.impl.repository.book.BookJpaRepository;

@Service
public class BookWriteServiceImpl implements BookWriteService {


	@Resource
	BookJpaRepository bookJpaRep;

	@Resource 
	IConverterFactory converterFactory;

	@Override
	public Book save(BookDTO bookDTO) {
		Book book = new Book();
		book = (Book)converterFactory.convertBeans(bookDTO, book);
		book = bookJpaRep.save(book);

		return book;

	}
	

	@Override
	public Book update(Long id, BookDTO bookDTO) {
		if(bookDTO.getId().longValue() != id.longValue()){
			throw new LibraryException("Cannot updated ID");
		}
		Book originalBook = bookJpaRep.findOne(id);

		Book updatedBook = (Book)converterFactory.convertBeans(bookDTO, originalBook);
						updatedBook = bookJpaRep.save(updatedBook);
		return updatedBook;

	}

	
	@Override
	public String delete(Long id) {
		Book book = bookJpaRep.findOne(id);
		String response = "Success";
		 bookJpaRep.delete(book);
		return response;
	}



	

	
	

}
