package com.example.demo.repository;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class MemberRepositoryTest {

	@Autowired
	TestEntityManager testEntityManager;
	
	@Autowired
	private IMemberRepository memberRepository;
	
	@Test
	public void testFullLoad( ) {
		Member member = new Member();
		member.setAge(20);
		member.setEmail("alice.dacosta@tours.fr");
		member.setNom("dacosta");
		member.setPrenom("alice");
		member.setPassword("123456");
		member.setRegisteredDate(new Date());
		Document document = new Document();
		document.setName("acte_de_naissance.txt");
		document.setContentType("text/plain");
		member.addDocument(document);
		testEntityManager.persist(member);
		List<Member> liste = memberRepository.findByEmail("alice.dacosta@tours.fr");
		assertEquals("Member en doublon : ", 1, liste.size());
		Member memberFromDB = memberRepository.fullLoad(liste.get(0).getId());
		assertEquals("Nom member inattendu", "dacosta", memberFromDB.getNom());
	}
}
