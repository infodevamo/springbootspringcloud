package com.example.demo.web.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.repository.IMemberRepository;
import com.example.demo.repository.Member;
import com.example.demo.web.controller.MemberRestController;

@RunWith(SpringRunner.class)
@WebMvcTest(value=MemberRestController.class, secure=false)
public class MemberRestControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private IMemberRepository memberRepository;
	
	@Test
	public void searchTest() throws Exception {

		Member member = new Member();
		member.setId(6L);
		member.setAge(20);
		member.setEmail("toto.dacosta@tours.fr");
		member.setNom("dacosta");
		member.setPrenom("toto");
		member.setPassword("123456");
		Optional<Member> memberOptional = Optional.of(member);
		
		BDDMockito.given(this.memberRepository.findById(6L)).willReturn(memberOptional);
		this.mockMvc.perform(get("/api/members/6").accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
	}
}
