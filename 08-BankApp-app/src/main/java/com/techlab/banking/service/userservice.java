package com.techlab.banking.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.techlab.banking.entites.userdetail;

public interface userservice {

	public void addUserDetail(userdetail user);

	UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
	
}
