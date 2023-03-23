package com.project.todo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import jakarta.annotation.security.PermitAll;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {
	
	public PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public UserDetailsService userDetailsService()
	{
		UserDetails normalUser=User
				.withUsername("sri")
				.password(passwordEncoder().encode("sri"))
				.roles("Normal")
				.build();
		
		UserDetails adminUser=User
				.withUsername("aju")
				.password(passwordEncoder().encode("aju"))
				.roles("Admin")
				.build();
		
		return new  InMemoryUserDetailsManager(normalUser,adminUser);
	}
	
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception
	{
		httpSecurity.csrf().disable()
		.authorizeHttpRequests()
//		.requestMatchers("/api/v1/admin")
//		.hasRole("Admin")
//		.requestMatchers("/api/v1/normal")
//		.hasRole("Normal")
//		.requestMatchers("/api/v1/home")
//		.permitAll()
		.anyRequest()
		.authenticated()
		.and()
		.formLogin();
		
		return httpSecurity.build();
	}
}
