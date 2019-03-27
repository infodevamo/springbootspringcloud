package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.repository.Customer;
import com.example.demo.repository.ICustomerMongoRepository;
import com.example.demo.repository.IMemberRepository;
import com.example.demo.repository.Member;

@SpringBootApplication
public class BootSpringDataT2Application implements CommandLineRunner{

	@Autowired
	IMemberRepository memberRepository;

	@Autowired
	ICustomerMongoRepository customerMongoRepository;

	public static void main(String[] args) {
		SpringApplication.run(BootSpringDataT2Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		searchInJpaTables();
		playWithMongo();
		List<Customer> customers = customerMongoRepository.findByLastNameContainingAllIgnoreCase("smith");
		System.out.println("Mongo Customers Size : " + customers.size());
	}


	private void playWithMongo() {
		customerMongoRepository.deleteAll();
		// save a couple of customers
		customerMongoRepository.save(new Customer("Alice", "Smith"));
		customerMongoRepository.save(new Customer("Bob", "Smith"));
		// fetch all customers
		System.out.println("Customers found with findAll():");
		System.out.println("-------------------------------");
		for (Customer customer : customerMongoRepository.findAll()) {
			System.out.println(customer);
		}
		System.out.println();
		// fetch an individual customer
		System.out.println("Customer found with findByFirstName('Alice'):");
		System.out.println("--------------------------------");
		System.out.println(customerMongoRepository.findByFirstName("Alice"));
		System.out.println("Customers found with findByLastName('Smith'):");
		System.out.println("--------------------------------");
		for (Customer customer : customerMongoRepository.findByLastName("Smith"))
		{
			System.out.println(customer);
		}
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
