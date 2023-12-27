package com.techlab.banking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techlab.banking.entites.Role;
import com.techlab.banking.repository.RoleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoleServiceIImpl implements RoleService {

	@Autowired
	private RoleRepository roleRepo;

	@Override
	public void AddRole(Role role) {
		roleRepo.save(role);
	}

}
