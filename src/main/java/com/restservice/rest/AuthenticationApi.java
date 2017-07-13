package com.restservice.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.restservice.security.User;
import com.restservice.security.UserRepository;

import io.swagger.annotations.ApiParam;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;


@CrossOrigin
@RestController
@RequestMapping("/api")
public class AuthenticationApi {
	@Autowired
	UserRepository userRepo;
	
	
	@RequestMapping(value="/authenticate", method=RequestMethod.GET, produces="application/json")
	@ResponseBody
	public String authenticate(	@RequestHeader@ApiParam(value="name to be retrieved",
								required=true,
								defaultValue="user")									
								String username
								) throws Exception{
		
		
		User user = userRepo.findByUsername(username);
		
		UsernamePasswordAuthenticationToken a = new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities());
		
		JSONObject json = new JSONObject();
		json.put("username", username);
		json.put("token", a);		
		
		
		
		return json.toString();
	}
	
	@RequestMapping(value="/whoami", method=RequestMethod.GET)
	public String whoami(){		
		return SecurityContextHolder.getContext().toString();
	}
	
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logout(){
		SecurityContextHolder.getContext().setAuthentication(null);		
		return "LOGGED OUT";
	}
}
