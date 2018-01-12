package com.devfeeds.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.devfeeds.entities.User;
import com.devfeeds.metier.UserMetier;
import java.security.Principal;

import javassist.NotFoundException;

@CrossOrigin("*")
@RestController
public class UserService {
	@Autowired
	UserMetier userMetier;
	
	
	@RequestMapping(value="/api/users",method=RequestMethod.GET)
    public List<User> getAllTopics(){
    	return userMetier.listUsers();
	}
	
	@RequestMapping(value="/api/signup",method=RequestMethod.POST)
    public User SaveUser(@RequestBody User user){
		return userMetier.saveUser(user);
	}
	

}
