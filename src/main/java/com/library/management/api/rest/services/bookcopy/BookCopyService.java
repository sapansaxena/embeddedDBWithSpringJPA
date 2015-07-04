package com.library.management.api.rest.services.bookcopy;

import javax.validation.Valid;

import com.library.management.api.dto.bookcopy.BookCopyDTO;



public interface  BookCopyService {	
	
		public  BookCopyDTO getBookCopy(Long  custId);
		

	    public BookCopyDTO createBookCopy(@Valid BookCopyDTO bookCopyDTO);
	 	
	 	
	    public BookCopyDTO updateBookCopy( Long  id, @Valid BookCopyDTO bookCopyDTO);
	 	
	 	
	    public String deleteBookCopy( Long id);


	
}
