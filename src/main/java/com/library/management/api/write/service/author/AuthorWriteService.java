package com.library.management.api.write.service.author;

import com.library.management.api.dto.author.AuthorDTO;
import com.library.management.api.entity.Author;

public interface  AuthorWriteService {
	
	public Author save(AuthorDTO author);
	public String delete(Long id);
	public Author update(Long id, AuthorDTO authorDTO);


}
