package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.repository.IMemberRepository;
import com.example.demo.repository.Member;

@SpringBootApplication
public class BootSpringMvcApplication implements CommandLineRunner {

	@Autowired
	IMemberRepository memberRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(BootSpringMvcApplication.class, args);
	}



	@Override
	public void run(String... args) throws Exception {
		searchInJpaTables();
	}



	private void searchInJpaTables() {
		List<Member> findAll = memberRepository.findAll();
		System.out.println("FindAll size : " + findAll.size());
		List<Member> findByEmail = memberRepository.findByEmail("toto@titi.com");
		System.out.println("findByEmail size : " + findByEmail.size());
		findByEmail.forEach(value -> {
			System.out.println("Age : " + value.getAge());
			System.out.println("Age : " + value.getNom());
		});
		Member fullLoad = memberRepository.fullLoad(1L);
		System.out.println("Age : " + fullLoad.getNom() + fullLoad.getDocuments());
	}
}
