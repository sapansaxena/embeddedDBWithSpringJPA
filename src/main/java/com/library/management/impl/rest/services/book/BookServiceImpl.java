package com.library.management.impl.rest.services.book;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.library.management.api.converter.IConverterFactory;
import com.library.management.api.dto.book.BookDTO;
import com.library.management.api.entity.Book;
import com.library.management.api.read.service.book.BookReadService;
import com.library.management.api.rest.services.book.BookService;
import com.library.management.api.write.service.book.BookWriteService;
import com.library.management.impl.log.Log;
import com.sun.media.jfxmedia.logging.Logger;


@RestController
@RequestMapping("/rest/")
@Service
public class  BookServiceImpl implements BookService  {

	@Inject
	BookReadService bookReadSvc;
	@Inject
	BookWriteService bookWriteSvc;
	
	
	@Inject 
	IConverterFactory converterFactory;

	String message = "";

	
	@Override
	@RequestMapping(value = "book/{id}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public @Valid BookDTO getBook(@PathVariable Long  id) {
		BookDTO bookDTO =bookReadSvc.findById(id);
		
		return bookDTO;
	}
	
	

	@Override
	@RequestMapping(value = "book", method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public BookDTO createBook(@Valid @RequestBody BookDTO bookDto) {
		Book book = bookWriteSvc.save(bookDto);
		if(book == null){
			Log.log(Logger.ERROR, "Book cant be created.");
			return new BookDTO();
		}
		
		message = "A new book is created with ID : " + book.getId();
		Log.log(Logger.INFO, message);
		
		bookDto = (BookDTO) converterFactory.convertBeans(book, bookDto);
		
		return  bookDto;
	}

	@Override
	@RequestMapping(value = "book/{id}", method = RequestMethod.PUT, produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public BookDTO updateBook(@PathVariable Long  id, @Valid @RequestBody BookDTO bookDto) {
		
		converterFactory.convertBeans(bookWriteSvc.update(id, bookDto), bookDto);
		
		return bookDto;
	}
	
	@Override
	@RequestMapping(value = "book/{id}", method = RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String  deleteBook(@PathVariable Long id) {
			
			
		Log.log(Logger.INFO, "Book with ID"+id+"deleted");
		
		String response = bookWriteSvc.delete(id);
		if(("Success".equalsIgnoreCase(response))){
			message = "Book with ID "+id+" is deleted.";
			Log.log(Logger.INFO, message);
		}
		
		return response;
	}
	
	 	
	 
	

}
