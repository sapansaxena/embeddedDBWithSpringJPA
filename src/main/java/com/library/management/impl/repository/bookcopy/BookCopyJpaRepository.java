package com.library.management.impl.repository.bookcopy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.library.management.api.entity.BookCopy;

@Repository
public interface BookCopyJpaRepository extends JpaRepository<BookCopy, Long> {
	
	

}
