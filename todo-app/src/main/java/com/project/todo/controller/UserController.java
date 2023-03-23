package com.project.todo.controller;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.todo.exception.ResourceNotFoundException;
import com.project.todo.model.User;
import com.project.todo.service.UserService;

//@CrossOrigin(origins="http://localhost:8080")
@RestController
@RequestMapping("/api/v1")
public class UserController {
	
	private UserService userService;
//	private JwtGeneratorInterface jwtGenerator;
	
	@Autowired
	  public UserController(UserService userService){
	    this.userService=userService;
//	    this.jwtGenerator=jwtGenerator;
	  }

	
	
	@GetMapping("/home")
	public ResponseEntity<String>home()
	{
		return ResponseEntity.ok("Welcome to our website! You are public user");
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/admin")
	public ResponseEntity<String>admin()
	{
		return ResponseEntity.ok("Welcome to our website! You are admin user");
	}
	@PreAuthorize("hasRole('USER')")
	@GetMapping("/normal")
	public ResponseEntity<String>normal()
	{
		return ResponseEntity.ok("Welcome to our website! You are normal user");
	}
	
	
	
	@PostMapping("/login/{id}")
	public ResponseEntity<UserDetails>login(@PathVariable("id") String phoneNumber)
	{
		try 
		{
		      if(phoneNumber == null) {
		      throw new ResourceNotFoundException("Phone Number is Empty");
		      }
		
		UserDetails userData=userService.loadUserByUsername(phoneNumber);
		
		if(userData == null){
		       throw new ResourceNotFoundException("Phone Number is Invalid");
		    }
//		       return new ResponseEntity<>(jwtGenerator.generateToken(phoneNumber), HttpStatus.OK);
//		return new ResponseEntity<>(userData,HttpStatus.OK);
		return ResponseEntity.ok(userData);
		} 
		catch (ResourceNotFoundException e) 
		{
//		       return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
			throw e;
		    
		}
	}
	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody User user)
	{
		try{
		     userService.saveUser(user);
		     return new ResponseEntity<>(user, HttpStatus.CREATED);
		   } catch (Exception e){
		     return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
		   }
		
	}	

}
