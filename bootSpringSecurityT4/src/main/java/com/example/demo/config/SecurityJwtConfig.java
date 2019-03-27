package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import com.example.demo.security.jwt.JWTConfigurer;
import com.example.demo.security.jwt.TokenProvider;

@Configuration
//@EnableWebSecurity(debug = true) //enable security debugging
@Profile("jwt")
public class SecurityJwtConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	TokenProvider tokenProvider;
	
	
	 @Bean
	    @Override
	    public AuthenticationManager authenticationManagerBean() throws Exception {
	        return super.authenticationManagerBean();
	    }
	 
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.exceptionHandling()
		.and()
		.csrf().disable() // Jeton csrf n’est plus nécessaire
		.headers().frameOptions().disable()
		.and() // Rien dans la session HTTP
		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.authorizeRequests() // ACLs
		.antMatchers("/api/authenticate").permitAll()
		.antMatchers("/", "/home").permitAll()
		.anyRequest().authenticated()
		.and()
		.apply(securityConfigurerAdapter()); // Configuration JWT
	}

	private JWTConfigurer securityConfigurerAdapter() {
		return new JWTConfigurer(tokenProvider);
	}
	
}
