package com.hospital.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.hospital.config.JwtTokenUtil;
import com.hospital.model.JwtRequest;
import com.hospital.model.LoginResponse;
import com.hospital.model.User;
import com.hospital.model.UserRegistration;
import com.hospital.model.VaccineRegistered;
import com.hospital.service.UserServices;
import com.hospital.service.VaccineRegisteredServices;



@RestController
@CrossOrigin
public class LoginController {
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private UserServices userDetailsService;
	@Autowired
	private VaccineRegisteredServices vaccineRegistredService;
	
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public LoginResponse createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
		System.out.println("USERRRRRRRR"+authenticationRequest.getUsername());

		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());

		final String token = jwtTokenUtil.generateToken(userDetails);
		User usr=userDetailsService.getUserDetails(authenticationRequest.getUsername());
		LoginResponse lgnRsp=new LoginResponse();
		lgnRsp.setToken(token);
		lgnRsp.setUserType(usr.getUserType());
		
		
	      System.out.println(token);
		
		return lgnRsp;

		//return ResponseEntity.ok(new JwtResponse(token));
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<?> saveUser(@RequestBody UserRegistration user) throws Exception {
		User loginUser=new User();
		loginUser.setUsername(user.getUsername());
		loginUser.setPassword(user.getPassword());
		
		if(user.getUserType().equalsIgnoreCase("RECIVER")){
			loginUser.setUserType("R");
			VaccineRegistered vaccineRegistered=new VaccineRegistered();
			vaccineRegistered.setEmail(user.getEmail());
			vaccineRegistered.setAddress(user.getAddress());
			vaccineRegistered.setAge(user.getAge());
			vaccineRegistered.setDescription(user.getDescription());
			vaccineRegistered.setFirstDose("2");// pending
			vaccineRegistered.setSecondDose("2");
			vaccineRegistered.setGovernmentId(user.getGovernmentId());
			vaccineRegistered.setName(user.getUsername());
			vaccineRegistered.setPhoneNumber(user.getPhone());
			vaccineRegistered.setState(user.getState());
			vaccineRegistredService.saveVaccineUser(vaccineRegistered);
			
			
		}
		
		
		System.out.println(loginUser.getUserType()+"User Type");
		
		return ResponseEntity.ok(userDetailsService.save(loginUser));
	}
	
	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}

}
