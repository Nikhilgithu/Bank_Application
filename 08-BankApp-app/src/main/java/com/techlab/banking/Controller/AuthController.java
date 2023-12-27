package com.techlab.banking.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techlab.banking.payload.JwtAuthResponse;
import com.techlab.banking.payload.LoginDto;
import com.techlab.banking.payload.RegisterDto;
import com.techlab.banking.service.AuthService;
import com.techlab.banking.service.userservice;

import lombok.RequiredArgsConstructor;
@CrossOrigin(origins="*")
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@RestController
public class AuthController {
	
	@Autowired
	private AuthService authService;
	@Autowired
	 private userservice userservice;
	 @Autowired
	    private AuthenticationManager authenticationManager;
	
	@PostMapping(value= {"/login","/sign in"})
	public ResponseEntity<JwtAuthResponse> login(@RequestBody LoginDto loginDto) {
		String token = authService.login(loginDto);

	    // Spring Security will handle loading user details based on the username
	    Authentication authentication = authenticationManager.authenticate(
	            new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));

	    // Retrieve authenticated user details
	    UserDetails userDetails = (UserDetails) authentication.getPrincipal();

	    boolean isCustomer = userDetails.getAuthorities().stream()
	            .anyMatch(authority -> authority.getAuthority().equals("ROLE_CUSTOMER"));

	    JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
	    jwtAuthResponse.setAccessToken(token);

	    if (isCustomer) {
	        jwtAuthResponse.setUserType("customer");
	    } else {
	        jwtAuthResponse.setUserType("admin");
	    }

	    return ResponseEntity.ok(jwtAuthResponse);
	}

	
	
	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody RegisterDto registerDto){
		String response=authService.register(registerDto);
		System.out.println(response);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
}
