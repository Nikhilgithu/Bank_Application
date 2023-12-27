package com.techlab.banking.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.techlab.banking.dto.CustomerDTO;
import com.techlab.banking.entites.Customer;
import com.techlab.banking.entites.userdetail;
import com.techlab.banking.repository.CustomerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepo;
	@Autowired
	private PasswordEncoder passwordencoder;

	@Override
	public void AddCustomer(Customer customer) {
		customerRepo.save(customer);
	}

	@Override
	public Customer getCustomerById(int customerId) {
		return customerRepo.findById(customerId).orElse(null);
	}

	@Override
	public void updateCustomer(Customer customer, int customerId) {
		System.out.println(customer);
		Optional<Customer> customer1 = customerRepo.findById(customerId);
		Customer existingCustomer = customer1.get();
		existingCustomer.setFirstName(customer.getFirstName());
		existingCustomer.setLastName(customer.getLastName());
		existingCustomer.setPassword(passwordencoder.encode(customer.getPassword()));

		// Update userdetail
		userdetail userDetail = existingCustomer.getUserDetail();
		userDetail.setUsername(customer.getFirstName());
		userDetail.setPassword(passwordencoder.encode(customer.getPassword()));

		customerRepo.save(existingCustomer);

	}

	@Override
	public List<CustomerDTO> getAllCustomers() {
		List<Customer> customers = customerRepo.findAll();
		List<CustomerDTO> customerDTOs = new ArrayList<>();
		for (Customer customer : customers) {
			CustomerDTO customerDTO = new CustomerDTO();
			customerDTO.setCustomerId(customer.getCustomerId());
			customerDTO.setFirstName(customer.getFirstName());
			customerDTO.setLastName(customer.getLastName());
			customerDTO.setAccounts(customer.getAccounts());
			customerDTO.setUserDetail(customer.getUserDetail());
			customerDTO.setRoles(customer.getRoles());
			customerDTOs.add(customerDTO);
		}
		return customerDTOs;
	}

	@Override
	public Long findCustomerIdByfirstName(String firstName) {
	    return customerRepo.findCustomerIdByfirstName(firstName);
	}



}
