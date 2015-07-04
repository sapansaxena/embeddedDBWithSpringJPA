package com.library.management.api.write.service.bookcopy;

import com.library.management.api.dto.bookcopy.BookCopyDTO;
import com.library.management.api.entity.BookCopy;

public interface  BookCopyWriteService {
	
	public BookCopy save(BookCopyDTO bookCopy);
	public String delete(Long id);
	public BookCopy update(Long id, BookCopyDTO bookCopyDTO);


}
