package com.library.management.api.rest.services.author;

import javax.validation.Valid;

import com.library.management.api.dto.author.AuthorDTO;



public interface  AuthorService {	
	
		public  AuthorDTO getAuthor(Long  custId);
		

	    public AuthorDTO createAuthor(@Valid AuthorDTO authorDTO);
	 	
	 	
	    public AuthorDTO updateAuthor( Long  id, @Valid AuthorDTO authorDTO);
	 	
	 	
	    public String deleteAuthor( Long id);


	
}
