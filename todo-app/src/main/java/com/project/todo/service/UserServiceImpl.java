package com.project.todo.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.todo.exception.ResourceNotFoundException;
import com.project.todo.model.User;
import com.project.todo.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;
	@Autowired
	  public UserServiceImpl(UserRepository userRepository){
	    this.userRepository=userRepository;
	  }
	  @Override
	  public void saveUser(User user) {
	    userRepository.save(user);
	  }
	  @Override
	  public Optional<User> getUserByPhoneNumber(String phoneNumber) throws ResourceNotFoundException {
	    Optional<User>  user = userRepository.findByPhoneNumber(phoneNumber);
	    if(user == null){
	       throw new ResourceNotFoundException("Invalid Phone Number");
	    }
	    return user;
	  }

}
