package com.techlab.banking.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techlab.banking.dto.AccountWithCustomerDTO;
import com.techlab.banking.entites.Account;
import com.techlab.banking.entites.Bank;
import com.techlab.banking.entites.Customer;
import com.techlab.banking.entites.Transaction;
import com.techlab.banking.entites.TransactionType;
import com.techlab.banking.repository.AccountRepository;
import com.techlab.banking.repository.BankRepository;
import com.techlab.banking.repository.TransactionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepository accountRepo;
	@Autowired
	private TransactionRepository transactionRepo;
	@Autowired
	private BankRepository bankRepo;

	@Override
	public Account createAccount(Customer customer, Bank bank, double balance) {
		Account account = new Account();
		account.setCustomer(customer);
		account.setBank(bank);
		account.setBalance(balance);

		bank.getAccounts().add(account);
		accountRepo.save(account);
		bankRepo.save(bank);

		return account;
	}

	@Override
	public void AddAccount(Account account) {
		accountRepo.save(account);
	}

	@Override
	public List<Account> GetAllAccount() {
		return accountRepo.findAll();
	}

	@Override
	public void withdraw(long accountNumber, double amount, TransactionType transactionType) {
		Account account = accountRepo.findByAccountNumber(accountNumber);
		if (account == null) {
			// Handle the case where the account does not exist or is not found
			// For example, you can throw an exception or return an appropriate response
			return;
		}

		if (account.getBalance() >= amount + 1000) {
			account.setBalance(account.getBalance() - amount);
			accountRepo.save(account);

			long millis = System.currentTimeMillis();
			java.sql.Date date = new java.sql.Date(millis);

			Transaction transaction = new Transaction();
			transaction.setTransactiontype(transactionType);
			transaction.setDate(date);
			transaction.setAmount(amount);
			transaction.setSenderAccount(account); // Same account as the sender
			transaction.setReceiverAccount(account); // Same account as the receiver

			transactionRepo.save(transaction);
		} else {
			// Handle the case where the account balance is insufficient for the withdrawal
			// For example, you can throw an exception or return an appropriate response
		}
	}

	@Override
	public void deposit(long accountNumber, double amount, TransactionType transactionType) {
		Account account = accountRepo.findByAccountNumber(accountNumber);
		if (account == null) {
			// Handle the case where the account does not exist or is not found
			// For example, you can throw an exception or return an appropriate response
			return;
		}

		account.setBalance(account.getBalance() + amount);
		accountRepo.save(account);

		long millis = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);

		Transaction transaction = new Transaction();
		transaction.setTransactiontype(transactionType);
		transaction.setDate(date);
		transaction.setAmount(amount);
		transaction.setSenderAccount(account); // Same account as the sender
		transaction.setReceiverAccount(account); // Same account as the receiver

		transactionRepo.save(transaction);
	}

	@Override
	public void transfer(long fromAccountNumber, long toAccountNumber, double amount, TransactionType transactionType) {
		Account fromAccount = accountRepo.findByAccountNumber(fromAccountNumber);
		Account toAccount = accountRepo.findByAccountNumber(toAccountNumber);

		if (fromAccount == null || toAccount == null) {
			// Handle the case where the accounts do not exist or are not found
			// For example, you can throw an exception or return an appropriate response
			return;
		}

		if (fromAccount.getBalance() >= amount + 1000) {
			fromAccount.setBalance(fromAccount.getBalance() - amount);
			toAccount.setBalance(toAccount.getBalance() + amount);

			accountRepo.save(fromAccount);
			accountRepo.save(toAccount);

			long millis = System.currentTimeMillis();
			java.sql.Date date = new java.sql.Date(millis);

			Transaction transaction = new Transaction();
			transaction.setTransactiontype(transactionType);
			transaction.setDate(date);
			transaction.setAmount(amount);
			transaction.setSenderAccount(fromAccount);
			transaction.setReceiverAccount(toAccount);

			transactionRepo.save(transaction);
		} else {

		}
	}

	@Override
	public List<AccountWithCustomerDTO> getAccountsWithCustomerDetailsByCustomerFirstName(String firstName) {
		Collection<Account> accounts = accountRepo.findByCustomer_FirstName(firstName);
		List<AccountWithCustomerDTO> accountDTOs = new ArrayList<>();

		for (Account account : accounts) {
			AccountWithCustomerDTO accountDTO = new AccountWithCustomerDTO();
			accountDTO.setAccountno(account.getAccountNumber());
			accountDTO.setBalance(account.getBalance());
			accountDTO.setCustomerId(account.getCustomer().getCustomerId());
			accountDTO.setBankName(account.getBank().getBankName());
			accountDTOs.add(accountDTO);
		}

		return accountDTOs;
	}

	@Override
	public List<Integer> getAccountNumbersByCustomerId(int customerId) {
		List<Account> accounts = accountRepo.findByCustomer_CustomerId(customerId);
		List<Integer> accountNumbers = new ArrayList<>();

		for (Account account : accounts) {
			accountNumbers.add(account.getAccountNumber());
		}

		return accountNumbers;
	}

}
