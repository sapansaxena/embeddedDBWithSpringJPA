package com.library.management.impl.rest.services.bookauthor;

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
import com.library.management.api.dto.bookauthor.BookAuthorDTO;
import com.library.management.api.entity.BookAuthor;
import com.library.management.api.read.service.bookauthor.BookAuthorReadService;
import com.library.management.api.rest.services.bookauthor.BookAuthorService;
import com.library.management.api.write.service.bookauthor.BookAuthorWriteService;
import com.library.management.impl.log.Log;
import com.sun.media.jfxmedia.logging.Logger;


@RestController
@RequestMapping("/rest/")
@Service
public class  BookAuthorServiceImpl implements BookAuthorService  {

	@Inject
	BookAuthorReadService bookAuthorReadSvc;
	@Inject
	BookAuthorWriteService bookAuthorWriteSvc;
	
	
	@Inject 
	IConverterFactory converterFactory;

	String message = "";

	
	@Override
	@RequestMapping(value = "bookAuthor/{id}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public @Valid BookAuthorDTO getBookAuthor(@PathVariable Long  id) {
		BookAuthorDTO bookAuthorDTO =bookAuthorReadSvc.findById(id);
		
		return bookAuthorDTO;
	}
	
	

	@Override
	@RequestMapping(value = "bookAuthor", method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public BookAuthorDTO createBookAuthor(@Valid @RequestBody BookAuthorDTO bookAuthorDto) {
		BookAuthor bookAuthor = bookAuthorWriteSvc.save(bookAuthorDto);
		if(bookAuthor == null){
			Log.log(Logger.ERROR, "BookAuthor cant be created.");
			return new BookAuthorDTO();
		}
		
		message = "A new bookAuthor is created with ID : " + bookAuthor.getId();
		Log.log(Logger.INFO, message);
		
		bookAuthorDto = (BookAuthorDTO) converterFactory.convertBeans(bookAuthor, bookAuthorDto);
		
		return  bookAuthorDto;
	}

	@Override
	@RequestMapping(value = "bookAuthor/{id}", method = RequestMethod.PUT, produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public BookAuthorDTO updateBookAuthor(@PathVariable Long  id, @Valid @RequestBody BookAuthorDTO bookAuthorDto) {
		
		converterFactory.convertBeans(bookAuthorWriteSvc.update(id, bookAuthorDto), bookAuthorDto);
		
		return bookAuthorDto;
	}
	
	@Override
	@RequestMapping(value = "bookAuthor/{id}", method = RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String  deleteBookAuthor(@PathVariable Long id) {
			
			
		Log.log(Logger.INFO, "BookAuthor with ID"+id+"deleted");
		
		String response = bookAuthorWriteSvc.delete(id);
		if(("Success".equalsIgnoreCase(response))){
			message = "BookAuthor with ID "+id+" is deleted.";
			Log.log(Logger.INFO, message);
		}
		
		return response;
	}
	
	 	
	 
	

}
