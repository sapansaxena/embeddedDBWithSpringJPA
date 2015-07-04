package com.library.management.api.read.service.bookcopy;


import java.util.List;

import com.library.management.api.dto.bookcopy.BookCopyDTO;

public interface BookCopyReadService {
	
	 BookCopyDTO findById(Long id);
	 
	 List<BookCopyDTO> findAll();
	 

}
