package com.techlab.banking.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.techlab.banking.entites.userdetail;
import com.techlab.banking.payload.LoginDto;
import com.techlab.banking.payload.RegisterDto;

public interface AuthService {


	String login(LoginDto loginDto);
	String register(RegisterDto registerDto);
	
}
