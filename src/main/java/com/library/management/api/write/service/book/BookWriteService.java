package com.library.management.api.write.service.book;

import com.library.management.api.dto.book.BookDTO;
import com.library.management.api.entity.Book;

public interface  BookWriteService {
	
	public Book save(BookDTO book);
	public String delete(Long id);
	public Book update(Long id, BookDTO bookDTO);


}
