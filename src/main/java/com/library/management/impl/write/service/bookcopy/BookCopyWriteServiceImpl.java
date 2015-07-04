package com.library.management.impl.write.service.bookcopy;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.library.management.api.converter.IConverterFactory;
import com.library.management.api.dto.bookcopy.BookCopyDTO;
import com.library.management.api.entity.BookCopy;
import com.library.management.api.rest.exception.handler.LibraryException;
import com.library.management.api.write.service.bookcopy.BookCopyWriteService;
import com.library.management.impl.repository.bookcopy.BookCopyJpaRepository;

@Service
public class BookCopyWriteServiceImpl implements BookCopyWriteService {


	@Resource
	BookCopyJpaRepository bookCopyJpaRep;

	@Resource 
	IConverterFactory converterFactory;

	@Override
	public BookCopy save(BookCopyDTO bookCopyDTO) {
		BookCopy bookCopy = new BookCopy();
		bookCopy = (BookCopy)converterFactory.convertBeans(bookCopyDTO, bookCopy);
		bookCopy = bookCopyJpaRep.save(bookCopy);

		return bookCopy;

	}
	

	@Override
	public BookCopy update(Long id, BookCopyDTO bookCopyDTO) {
		if(bookCopyDTO.getId().longValue() != id.longValue()){
			throw new LibraryException("Cannot updated ID");
		}
		BookCopy originalBookCopy = bookCopyJpaRep.findOne(id);

		BookCopy updatedBookCopy = (BookCopy)converterFactory.convertBeans(bookCopyDTO, originalBookCopy);
						updatedBookCopy = bookCopyJpaRep.save(updatedBookCopy);
		return updatedBookCopy;

	}

	
	@Override
	public String delete(Long id) {
		BookCopy bookCopy = bookCopyJpaRep.findOne(id);
		String response = "Success";
		 bookCopyJpaRep.delete(bookCopy);
		return response;
	}



	

	
	

}
