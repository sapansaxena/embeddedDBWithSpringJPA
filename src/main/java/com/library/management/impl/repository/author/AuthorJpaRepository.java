package com.library.management.impl.repository.author;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.library.management.api.entity.Author;

@Repository
public interface AuthorJpaRepository extends JpaRepository<Author, Long> {
	
	

}
