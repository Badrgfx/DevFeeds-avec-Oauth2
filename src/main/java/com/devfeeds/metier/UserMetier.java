package com.devfeeds.metier;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.devfeeds.entities.User;

public interface UserMetier {
	
	public List<User> listUsers();
	public User saveUser(User user);
	public void deleteUser(Long id);
	public User findUser(Long id);
	public UserDetails loadUserByUsername(String username);



}
