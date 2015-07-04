package com.library.management.impl.repository.member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.library.management.api.entity.Member;

@Repository
public interface MemberJpaRepository extends JpaRepository<Member, Long> {
	
	

}
