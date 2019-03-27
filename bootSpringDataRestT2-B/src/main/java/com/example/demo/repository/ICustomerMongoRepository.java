package com.example.demo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ICustomerMongoRepository extends MongoRepository<Customer, Long>{

//	List<Customer> findByFirstNameOrLastNameContainingAllIgnoreCase(String name);
	List<Customer> findByLastNameContainingAllIgnoreCase(String name);

	List<Customer> findByFirstName(String string);

	List<Customer> findByLastName(String string);
}
