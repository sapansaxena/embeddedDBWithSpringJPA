package com.library.management.api.rest.services.bookauthor;

import javax.validation.Valid;

import com.library.management.api.dto.bookauthor.BookAuthorDTO;



public interface  BookAuthorService {	
	
		public  BookAuthorDTO getBookAuthor(Long  custId);
		

	    public BookAuthorDTO createBookAuthor(@Valid BookAuthorDTO bookAuthorDTO);
	 	
	 	
	    public BookAuthorDTO updateBookAuthor( Long  id, @Valid BookAuthorDTO bookAuthorDTO);
	 	
	 	
	    public String deleteBookAuthor( Long id);


	
}
