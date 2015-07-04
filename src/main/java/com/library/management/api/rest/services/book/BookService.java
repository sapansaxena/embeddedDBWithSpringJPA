package com.library.management.api.rest.services.book;

import javax.validation.Valid;

import com.library.management.api.dto.book.BookDTO;



public interface  BookService {	
	
		public  BookDTO getBook(Long  custId);
		

	    public BookDTO createBook(@Valid BookDTO BookDTO);
	 	
	 	
	    public BookDTO updateBook( Long  id, @Valid BookDTO BookDTO);
	 	
	 	
	    public String deleteBook( Long id);


	
}
