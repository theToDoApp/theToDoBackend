package com.project.todo.config;

import java.util.Map;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.HashMap;


@Service
public class JwtGeneratorImpl implements JwtGeneratorInterface {

	@Value("${jwt.secret}")
	  private String secret;
	  @Value("${app.jwttoken.message}")
	  private String message;
	  @Override
	  public Map<String, String> generateToken(String phoneNumber) {
	    String jwtToken="";
	    jwtToken = Jwts.builder().setSubject(phoneNumber).setIssuedAt(new Date()).signWith(SignatureAlgorithm.HS256, "secret").compact();
	    Map<String, String> jwtTokenGen = new HashMap<>();
	    jwtTokenGen.put("token", jwtToken);
	    jwtTokenGen.put("message", message);
	    return jwtTokenGen;
	  }

}
