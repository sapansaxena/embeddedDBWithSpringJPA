package com.library.management.impl.write.service.bookauthor;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.library.management.api.converter.IConverterFactory;
import com.library.management.api.dto.bookauthor.BookAuthorDTO;
import com.library.management.api.entity.BookAuthor;
import com.library.management.api.rest.exception.handler.LibraryException;
import com.library.management.api.write.service.bookauthor.BookAuthorWriteService;
import com.library.management.impl.repository.bookauthor.BookAuthorJpaRepository;

@Service
public class BookAuthorWriteServiceImpl implements BookAuthorWriteService {


	@Resource
	BookAuthorJpaRepository bookAuthorJpaRep;

	@Resource 
	IConverterFactory converterFactory;

	@Override
	public BookAuthor save(BookAuthorDTO bookAuthorDTO) {
		BookAuthor bookAuthor = new BookAuthor();
		bookAuthor = (BookAuthor)converterFactory.convertBeans(bookAuthorDTO, bookAuthor);
		bookAuthor = bookAuthorJpaRep.save(bookAuthor);

		return bookAuthor;

	}
	

	@Override
	public BookAuthor update(Long id, BookAuthorDTO bookAuthorDTO) {
		if(bookAuthorDTO.getId().longValue() != id.longValue()){
			throw new LibraryException("Cannot updated ID");
		}
		BookAuthor originalBookAuthor = bookAuthorJpaRep.findOne(id);

		BookAuthor updatedBookAuthor = (BookAuthor)converterFactory.convertBeans(bookAuthorDTO, originalBookAuthor);
						updatedBookAuthor = bookAuthorJpaRep.save(updatedBookAuthor);
		return updatedBookAuthor;

	}

	
	@Override
	public String delete(Long id) {
		BookAuthor bookAuthor = bookAuthorJpaRep.findOne(id);
		String response = "Success";
		 bookAuthorJpaRep.delete(bookAuthor);
		return response;
	}



	

	
	

}
