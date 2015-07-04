package com.library.management.api.read.service.bookauthor;


import java.util.List;

import com.library.management.api.dto.bookauthor.BookAuthorDTO;

public interface BookAuthorReadService {
	
	 BookAuthorDTO findById(Long id);
	 
	 List<BookAuthorDTO> findAll();
	 

}
