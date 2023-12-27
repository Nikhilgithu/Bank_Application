package com.techlab.banking.service;

import java.util.List;

import com.techlab.banking.dto.AccountWithCustomerDTO;
import com.techlab.banking.entites.Account;
import com.techlab.banking.entites.Bank;
import com.techlab.banking.entites.Customer;
import com.techlab.banking.entites.TransactionType;

public interface AccountService {

	void AddAccount(Account account);
	List <Account> GetAllAccount();
	Account createAccount(Customer customer, Bank bank, double balance);
	 void withdraw(long fromAccountNumber, double amount, TransactionType transactionType);
	    void deposit(long toAccountNumber, double amount, TransactionType transactionType);
	    void transfer(long fromAccountNumber, long toAccountNumber, double amount, TransactionType transactionType);
	    List<Integer> getAccountNumbersByCustomerId(int customerId);
	    List<AccountWithCustomerDTO> getAccountsWithCustomerDetailsByCustomerFirstName(String firstName);
}
