package com.techlab.banking.service;

import java.util.List;

import com.techlab.banking.dto.CustomerDTO;
import com.techlab.banking.entites.Customer;

public interface CustomerService {
	void AddCustomer(Customer customer);
	

	Customer getCustomerById(int customerId);
	void updateCustomer(Customer customer,int customerId);
	 List<CustomerDTO> getAllCustomers();


	 Long findCustomerIdByfirstName(String firstName);

}
