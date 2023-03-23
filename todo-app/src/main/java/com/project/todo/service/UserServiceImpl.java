package com.project.todo.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.todo.exception.ResourceNotFoundException;
import com.project.todo.model.User;
import com.project.todo.repository.MyUserPrincipal;
import com.project.todo.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService,UserDetailsService {

	private UserRepository userRepository;
//	@Autowired
//	  public UserServiceImpl(UserRepository userRepository){
//	    this.userRepository=userRepository;
//	  }
	  @Override
	  public void saveUser(User user) {
	    userRepository.save(user);
	  }
//	  @Override
//	  public User getUserByPhoneNumber(String phoneNumber) throws ResourceNotFoundException {
//	    User  user = userRepository.findByPhoneNumber(phoneNumber);
//	    if(user == null){
//	       throw new ResourceNotFoundException("Invalid Phone Number");
//	    }
//	    return user;
//	  }
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user = userRepository.findByPhoneNumber(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new MyUserPrincipal(user);
	}

}
