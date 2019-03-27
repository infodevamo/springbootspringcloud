package com.example.demo;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("hello")
public class HelloProperties {

	@NotEmpty
	private String greeting = "Welcome";

	//@Pattern(regexp="A|B|C")
	private String styleCase;
	
	@Min(0)
	@Max(1)
	private int position;
	
	public String getGreeting() {
		return greeting;
	}

	public void setGreeting(String greeting) {
		this.greeting = greeting;
	}
	
	public String getStyleCase() {
		return greeting;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public void setStyleCase(String styleCase) {
		this.styleCase = styleCase;
	}
}
