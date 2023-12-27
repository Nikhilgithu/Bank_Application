package com.techlab.banking.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techlab.banking.dto.CustomerDTO;
import com.techlab.banking.entites.Customer;
import com.techlab.banking.service.CustomerService;

import lombok.RequiredArgsConstructor;
@CrossOrigin(origins="*")
@RestController
@RequestMapping("/BankApp")
@RequiredArgsConstructor
public class CustomerCotroller {
	
	@Autowired
	private CustomerService customerservice;
	
	@PostMapping("/Addcustomer")
	public void AddCustomer(@RequestBody Customer customer)
	{
		customerservice.AddCustomer(customer);
	}
	
	
	
	@GetMapping("/GetAllCustomer")
    public List<CustomerDTO> getAllCustomers() {
        return customerservice.getAllCustomers();
    }
	
	 @PutMapping("/{customerId}")
	    public String updateCustomer(@RequestBody Customer customer, @PathVariable int customerId) {
		 customerservice.updateCustomer(customer, customerId);
	        return "Customer updated successfully";
	    }
	 
	 @GetMapping("/getcustomer-id")
	    public ResponseEntity<Long> getCustomerIdByUsername(@RequestParam String firstName) {
	        Long customerId = customerservice.findCustomerIdByfirstName(firstName);
	        if (customerId != null) {
	            return ResponseEntity.ok(customerId);
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    }

}
 