package com.techlab.banking.service;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.techlab.banking.entites.Customer;
import com.techlab.banking.entites.Role;
import com.techlab.banking.entites.userdetail;
import com.techlab.banking.exception.UserAPIException;
import com.techlab.banking.payload.LoginDto;
import com.techlab.banking.payload.RegisterDto;
import com.techlab.banking.repository.CustomerRepository;
import com.techlab.banking.repository.RoleRepository;
import com.techlab.banking.security.JwtTokenProvider;


@Service
public class AuthServiceImpl  implements AuthService{
	 private AuthenticationManager authenticationManager;
	    private CustomerRepository customerRepository;
	    private RoleRepository roleRepository;
	    private JwtTokenProvider jwtTokenProvider;
	    private PasswordEncoder passwordEncoder;

	    public AuthServiceImpl(AuthenticationManager authenticationManager, CustomerRepository customerRepository,
	                           RoleRepository roleRepository, JwtTokenProvider jwtTokenProvider, PasswordEncoder passwordEncoder) {
	        this.authenticationManager = authenticationManager;
	        this.customerRepository = customerRepository;
	        this.roleRepository = roleRepository;
	        this.jwtTokenProvider = jwtTokenProvider;
	        this.passwordEncoder = passwordEncoder;
	    }

	    @Override
	    public String login(LoginDto loginDto) {
	        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
	                loginDto.getUsername(), loginDto.getPassword()));

	        SecurityContextHolder.getContext().setAuthentication(authentication);
	        String token = jwtTokenProvider.generateToken(authentication);
	        return token;
	    }
	    

	    @Override
	    public String register(RegisterDto registerDto) {
	        if (customerRepository.existsByFirstName(registerDto.getFirstname())) {
	            throw new UserAPIException(HttpStatus.BAD_REQUEST, "Customer already exists");
	        }

	        Customer customer = new Customer();
	        userdetail ud=new userdetail();
	        customer.setFirstName(registerDto.getFirstname());
	        customer.setLastName(registerDto.getLastname());
	        customer.setPassword(passwordEncoder.encode(registerDto.getPassword()));

	        List<Role> roles = new ArrayList<>();
	        Role customerRole = roleRepository.findByRolename("ROLE_CUSTOMER");
	        
	        roles.add(customerRole);
	        customer.setRoles(roles);

	        customerRepository.save(customer);
	       // customerRepository.save(ud);
	        return "customer registered successfully";
	    }
}
