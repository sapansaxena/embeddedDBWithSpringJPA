package com.library.management.api.read.service.author;


import java.util.List;

import com.library.management.api.dto.author.AuthorDTO;

public interface AuthorReadService {
	
	 AuthorDTO findById(Long id);
	 
	 List<AuthorDTO> findAll();
	 

}
