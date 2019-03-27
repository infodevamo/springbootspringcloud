package com.example.demo.web.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.repository.Document;
import com.example.demo.repository.IMemberRepository;
import com.example.demo.repository.Member;

@RestController
@RequestMapping("/api/members")
public class MemberRestController {

	@Autowired
	IMemberRepository memberRepository;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)	
	public ResponseEntity<Member> loadMemberById(@PathVariable Long id) {
		Optional<Member> member = memberRepository.findById(id);
		ResponseEntity<Member> result = member.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElse(new ResponseEntity<Member>(HttpStatus.NOT_FOUND));
		return result;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.POST)	
	public ResponseEntity<Member> addDocumentToMember(@PathVariable Long id, @RequestBody Document document) {
		Member member = memberRepository.fullLoad(id);
		if(member != null) {
			
			member.addDocument(document);
			memberRepository.save(member);
			return new ResponseEntity<>(HttpStatus.CREATED);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
