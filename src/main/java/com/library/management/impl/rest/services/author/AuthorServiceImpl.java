package com.library.management.impl.rest.services.author;

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
import com.library.management.api.dto.author.AuthorDTO;
import com.library.management.api.entity.Author;
import com.library.management.api.read.service.author.AuthorReadService;
import com.library.management.api.rest.services.author.AuthorService;
import com.library.management.api.write.service.author.AuthorWriteService;
import com.library.management.impl.log.Log;
import com.sun.media.jfxmedia.logging.Logger;


@RestController
@RequestMapping("/rest/")
@Service
public class  AuthorServiceImpl implements AuthorService  {

	@Inject
	AuthorReadService authorReadSvc;
	@Inject
	AuthorWriteService authorWriteSvc;
	
	
	@Inject 
	IConverterFactory converterFactory;

	String message = "";

	
	@Override
	@RequestMapping(value = "author/{id}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public @Valid AuthorDTO getAuthor(@PathVariable Long  id) {
		AuthorDTO authorDTO =authorReadSvc.findById(id);
		
		return authorDTO;
	}
	
	

	@Override
	@RequestMapping(value = "author", method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public AuthorDTO createAuthor(@Valid @RequestBody AuthorDTO authorDto) {
		Author author = authorWriteSvc.save(authorDto);
		if(author == null){
			Log.log(Logger.ERROR, "Author cant be created.");
			return new AuthorDTO();
		}
		
		message = "A new author is created with ID : " + author.getId();
		Log.log(Logger.INFO, message);
		
		authorDto = (AuthorDTO) converterFactory.convertBeans(author, authorDto);
		
		return  authorDto;
	}

	@Override
	@RequestMapping(value = "author/{id}", method = RequestMethod.PUT, produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public AuthorDTO updateAuthor(@PathVariable Long  id, @Valid @RequestBody AuthorDTO authorDto) {
		
		converterFactory.convertBeans(authorWriteSvc.update(id, authorDto), authorDto);
		
		return authorDto;
	}
	
	@Override
	@RequestMapping(value = "author/{id}", method = RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String  deleteAuthor(@PathVariable Long id) {
			
			
		Log.log(Logger.INFO, "Author with ID"+id+"deleted");
		
		String response = authorWriteSvc.delete(id);
		if(("Success".equalsIgnoreCase(response))){
			message = "Author with ID "+id+" is deleted.";
			Log.log(Logger.INFO, message);
		}
		
		return response;
	}
	
	 	
	 
	

}
