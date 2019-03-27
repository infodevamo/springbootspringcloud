package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class BootAutoconfigT1Application {

	public static void main(String[] args) {
		ConfigurableApplicationContext run = SpringApplication.run(BootAutoconfigT1Application.class, args);
		String[] beanDefinitionNames = run.getBeanDefinitionNames();
		int count = 1;
		for(String str : beanDefinitionNames) {
			
			System.out.println("Bean name " + count + " : " + str);
			count ++;
		}
	}

}
