package com.techlab.banking.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techlab.banking.entites.Account;

public interface AccountRepository extends JpaRepository<Account,Integer>{

	Account getAccountByAccountNumber(long fromAccNo);

	Account findByAccountNumber(long fromAccountNumber);

	Collection<Account> findByCustomer_FirstName(String firstName);
	List<Account> findByCustomer_CustomerId(int customerId);
}
