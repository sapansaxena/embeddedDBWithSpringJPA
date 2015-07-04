package com.library.management.api.read.service.book;


import java.util.List;

import com.library.management.api.dto.book.BookDTO;

public interface BookReadService {
	
	 BookDTO findById(Long id);
	 
	 List<BookDTO> findAll();
	 

}
