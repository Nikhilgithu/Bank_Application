package com.techlab.banking.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techlab.banking.entites.userdetail;
import com.techlab.banking.service.userservice;

import lombok.RequiredArgsConstructor;
@CrossOrigin(origins="*")
@RestController
@RequestMapping("/BankApp")
@RequiredArgsConstructor
public class userController {

	@Autowired
	 private userservice userservice;

	    @PostMapping("/Adduser")
	    public void addUserDetail(@RequestBody userdetail user) {
	    	userservice.addUserDetail(user);
	    }
	
}
