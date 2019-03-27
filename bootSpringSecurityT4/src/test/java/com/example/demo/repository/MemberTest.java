package com.example.demo.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@JsonTest
public class MemberTest {

	@Autowired
	private JacksonTester<Member> jsonMember;
	
	@Test
	public void serialisationTest() throws IOException {
		Member member = new Member();
		member.setAge(20);
		member.setEmail("alice.dacosta@tours.fr");
		member.setNom("dacosta");
		member.setPrenom("alice");
		member.setPassword("123456");
		Document document = new Document();
		document.setName("acte_de_naissance.txt");
		document.setContentType("text/plain");
		member.addDocument(document);
		JsonContent<Member> write = this.jsonMember.write(member);
		System.out.println(write.toString());
		assertThat(write).isEqualToJson("member.json");
		
		assertThat(write).hasJsonPathStringValue("@.email");
		assertThat(write).extractingJsonPathStringValue("@.email")
		.isEqualTo("alice.dacosta@tours.fr");
		
	}
	
	
	@Test
	public void testDeserialize() throws Exception {
	String content = "{\r\n" + 
			"	\"id\": 0,\r\n" + 
			"	\"email\": \"alice.dacosta@tours.fr\",\r\n" + 
			"	\"password\": \"123456\",\r\n" + 
			"	\"nom\": \"dacosta\",\r\n" + 
			"	\"prenom\": \"alice\",\r\n" + 
			"	\"age\": 20,\r\n" + 
			"	\"registeredDate\": null,\r\n" + 
			"	\"nomComplet\": \"alice dacosta\"\r\n" + 
			"}";
	Member member = new Member();
	member.setAge(20);
	member.setEmail("alice.dacosta@tours.fr");
	member.setNom("dacosta");
	member.setPrenom("alice");
	member.setPassword("123456");
	assertThat(this.jsonMember.parse(content))
	.isEqualTo(member);
	assertThat(this.jsonMember.parseObject(content).getNom()).isEqualTo("dacosta");
	}
}
