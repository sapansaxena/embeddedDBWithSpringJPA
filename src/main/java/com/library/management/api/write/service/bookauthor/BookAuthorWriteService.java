package com.library.management.api.write.service.bookauthor;

import com.library.management.api.dto.bookauthor.BookAuthorDTO;
import com.library.management.api.entity.BookAuthor;

public interface  BookAuthorWriteService {
	
	public BookAuthor save(BookAuthorDTO bookAuthor);
	public String delete(Long id);
	public BookAuthor update(Long id, BookAuthorDTO bookAuthorDTO);


}
