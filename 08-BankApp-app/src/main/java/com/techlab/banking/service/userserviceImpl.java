package com.techlab.banking.service;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.techlab.banking.entites.userdetail;
import com.techlab.banking.repository.CustomerRepository;
import com.techlab.banking.repository.UserDetailRepository;

@Service
public class userserviceImpl implements userservice{

	@Autowired
	private  UserDetailRepository userRepo;
	@Autowired
	private CustomerRepository customerRepo;

    @Override
    public void addUserDetail(userdetail user) {
    	userRepo.save(user);
}

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        userdetail userDetail = userRepo.findByusername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        Set<GrantedAuthority> authorities = userDetail.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getRolename()))
                .collect(Collectors.toSet());

        return new org.springframework.security.core.userdetails.User(
                userDetail.getUsername(),
                userDetail.getPassword(),
                authorities
        );
    }

    
    
}
