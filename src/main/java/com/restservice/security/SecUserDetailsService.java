package com.restservice.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class SecUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	public void CreateUser(User user){
		userRepository.save(user);
	}
	
	public void deleteAll(){
		userRepository.deleteAll();
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//Here add user data layer fetching from the MongoDB.I have used userRepository
		User user = userRepository.findByUsername(username);
		
		if (user == null) {
			throw new UsernameNotFoundException(username);
		} else {
			UserDetails details = user;
			
			System.out.println("USDS:" + details);
			
			return details;
		}
	}
}