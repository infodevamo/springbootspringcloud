package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface IMemberRepository extends JpaRepository<Member, Long>{

	List<Member> findByEmail(String email);
	
	@Query("select m from Member m left join fetch m.documents where m.id=?1")
	public Member fullLoad(Long id);
}
