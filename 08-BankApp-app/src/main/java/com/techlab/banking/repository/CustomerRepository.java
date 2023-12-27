package com.techlab.banking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.techlab.banking.entites.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	//Optional<Customer> findByFirstName(String firstName);
//
	boolean existsByFirstName(String firstName);
	@Query("SELECT c.customerId FROM Customer c WHERE c.firstName = :firstName")
	Long findCustomerIdByfirstName(@Param("firstName") String firstName);

	//void save(userdetail ud);
}
