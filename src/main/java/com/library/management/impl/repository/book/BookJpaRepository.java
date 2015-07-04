package com.library.management.impl.repository.book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.library.management.api.entity.Book;

@Repository
public interface BookJpaRepository extends JpaRepository<Book, Long> {
	
	

}
