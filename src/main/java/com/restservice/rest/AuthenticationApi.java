package com.restservice.rest;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class AuthenticationApi {
		
	@RequestMapping(value="/whoami", method=RequestMethod.GET)
	public String whoami(){		
		return SecurityContextHolder.getContext().toString();
	}
	
	@RequestMapping(value="/logout", method=RequestMethod.POST)
	public String logout(){
		SecurityContextHolder.getContext().setAuthentication(null);		
		return "LOGGED OUT";
	}
}
