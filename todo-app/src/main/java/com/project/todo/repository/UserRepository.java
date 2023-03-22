package com.project.todo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.todo.model.User;

@Repository
public interface UserRepository extends JpaRepository<User,String>{

	Optional<User> findByPhoneNumber(String phoneNumber);
	
	

}
