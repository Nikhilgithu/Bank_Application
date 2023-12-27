package com.techlab.banking.security;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.techlab.banking.entites.Customer;
import com.techlab.banking.entites.userdetail;
import com.techlab.banking.repository.CustomerRepository;
import com.techlab.banking.repository.UserDetailRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	private CustomerRepository customerRepository;
	private UserDetailRepository udrepo;

//    public CustomUserDetailsService(CustomerRepository customerRepository) {
//        this.customerRepository = customerRepository;
//    }

	public CustomUserDetailsService(UserDetailRepository udrepo) {
		super();
		this.udrepo = udrepo;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		userdetail userDetail = udrepo.findByusername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

		Set<GrantedAuthority> authorities = userDetail.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getRolename())).collect(Collectors.toSet());

		boolean isCustomer = userDetail.getRoles().stream().anyMatch(role -> role.getRoleid() == 1); // Assuming roleid
																										// 1 corresponds
																										// to customer

		String userType = isCustomer ? "customer" : "admin";

		return new org.springframework.security.core.userdetails.User(userDetail.getUsername(),
				userDetail.getPassword(), authorities);
	}

}
