package com.devfeeds.metier;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.devfeeds.dao.UserRepository;
import com.devfeeds.entities.User;

@Service("userDetailsService")
public class UserMetierImpl implements UserMetier, UserDetailsService{
	@Autowired
	private UserRepository userRepository;

	@Override
	public List<User> listUsers() {
		return userRepository.findAll();
	}

	@Override
	public User saveUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public void deleteUser(Long id) {
		userRepository.delete(id);
		
	}

	@Override
	public User findUser(Long id) {
		return userRepository.findOne(id);
	}
	

	@Override
	public UserDetails loadUserByUsername(String username){
		return userRepository.findOneByUsername(username);
	}

}
