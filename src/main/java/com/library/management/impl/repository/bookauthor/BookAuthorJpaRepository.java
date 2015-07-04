package com.library.management.impl.repository.bookauthor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.library.management.api.entity.BookAuthor;

@Repository
public interface BookAuthorJpaRepository extends JpaRepository<BookAuthor, Long> {
	
	

}
