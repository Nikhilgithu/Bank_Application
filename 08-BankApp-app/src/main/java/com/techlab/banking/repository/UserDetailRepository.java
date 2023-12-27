package com.techlab.banking.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techlab.banking.entites.Customer;
import com.techlab.banking.entites.userdetail;

public interface UserDetailRepository extends JpaRepository<userdetail, Integer>{

	Optional<userdetail> findByusername(String username);
	boolean existsByUsername(String username);
	
}
