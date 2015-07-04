package com.library.management.impl.rest.services.bookcopy;

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
import com.library.management.api.dto.bookcopy.BookCopyDTO;
import com.library.management.api.entity.BookCopy;
import com.library.management.api.read.service.bookcopy.BookCopyReadService;
import com.library.management.api.rest.services.bookcopy.BookCopyService;
import com.library.management.api.write.service.bookcopy.BookCopyWriteService;
import com.library.management.impl.log.Log;
import com.sun.media.jfxmedia.logging.Logger;


@RestController
@RequestMapping("/rest/")
@Service
public class  BookCopyServiceImpl implements BookCopyService  {

	@Inject
	BookCopyReadService bookCopyReadSvc;
	@Inject
	BookCopyWriteService bookCopyWriteSvc;
	
	
	@Inject 
	IConverterFactory converterFactory;

	String message = "";

	
	@Override
	@RequestMapping(value = "bookCopy/{id}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public @Valid BookCopyDTO getBookCopy(@PathVariable Long  id) {
		BookCopyDTO bookCopyDTO =bookCopyReadSvc.findById(id);
		
		return bookCopyDTO;
	}
	
	

	@Override
	@RequestMapping(value = "bookCopy", method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public BookCopyDTO createBookCopy(@Valid @RequestBody BookCopyDTO bookCopyDto) {
		BookCopy bookCopy = bookCopyWriteSvc.save(bookCopyDto);
		if(bookCopy == null){
			Log.log(Logger.ERROR, "BookCopy cant be created.");
			return new BookCopyDTO();
		}
		
		message = "A new bookCopy is created with ID : " + bookCopy.getId();
		Log.log(Logger.INFO, message);
		
		bookCopyDto = (BookCopyDTO) converterFactory.convertBeans(bookCopy, bookCopyDto);
		
		return  bookCopyDto;
	}

	@Override
	@RequestMapping(value = "bookCopy/{id}", method = RequestMethod.PUT, produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public BookCopyDTO updateBookCopy(@PathVariable Long  id, @Valid @RequestBody BookCopyDTO bookCopyDto) {
		
		converterFactory.convertBeans(bookCopyWriteSvc.update(id, bookCopyDto), bookCopyDto);
		
		return bookCopyDto;
	}
	
	@Override
	@RequestMapping(value = "bookCopy/{id}", method = RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String  deleteBookCopy(@PathVariable Long id) {
			
			
		Log.log(Logger.INFO, "BookCopy with ID"+id+"deleted");
		
		String response = bookCopyWriteSvc.delete(id);
		if(("Success".equalsIgnoreCase(response))){
			message = "BookCopy with ID "+id+" is deleted.";
			Log.log(Logger.INFO, message);
		}
		
		return response;
	}
	
	 	
	 
	

}
