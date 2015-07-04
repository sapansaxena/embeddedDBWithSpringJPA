package com.library.management.impl.repository.membercopy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.library.management.api.entity.MemberCopy;

@Repository
public interface MemberCopyJpaRepository extends JpaRepository<MemberCopy, Long> {
	
	

}
