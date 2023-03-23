package com.project.todo.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import com.project.todo.model.User;
@Service
public interface UserService{

	public void saveUser(User user);
//    public User getUserByPhoneNumber(String phoneNumber) throws ResourceNotFoundException;
	
	UserDetails loadUserByUsername(String username);
	
}
