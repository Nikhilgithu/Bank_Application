package com.techlab.banking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techlab.banking.entites.Role;

public interface RoleRepository extends JpaRepository<Role,String>{
	Role findByRolename(String rolename);
}
