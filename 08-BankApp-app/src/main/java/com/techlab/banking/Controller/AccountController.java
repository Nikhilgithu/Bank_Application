package com.techlab.banking.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techlab.banking.dto.AccountWithCustomerDTO;
import com.techlab.banking.entites.Account;
import com.techlab.banking.entites.Bank;
import com.techlab.banking.entites.Customer;
import com.techlab.banking.entites.TransactionType;
import com.techlab.banking.service.AccountRequest;
import com.techlab.banking.service.AccountService;
import com.techlab.banking.service.BankService;
import com.techlab.banking.service.CustomerService;
import com.techlab.banking.service.TransactionService;
import com.techlab.banking.service.TransactionTypeService;

import lombok.RequiredArgsConstructor;
@CrossOrigin(origins="*")
@RestController
@RequestMapping("/BankApp")
@RequiredArgsConstructor
public class AccountController {

	@Autowired
	private AccountService accountservice;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private BankService bankService;
	@Autowired
	private TransactionService transactionservice;
	@Autowired
	private TransactionTypeService transactiontyservice;

//	@PostMapping("/Addaccounts")
//	public void addAccount(@RequestBody AccountRequest request) {
//		Customer customer = customerService.getCustomerById(request.getCustomer_id());
//		Bank bank = bankService.getBankById(request.getBankId());
//		Account account = accountservice.createAccount(customer, bank, request.getBalance());
//		accountservice.AddAccount(account);
//	}

	@GetMapping("/getaccounts")
	public List<Account> GetAllAccunt() {
		return accountservice.GetAllAccount();

	}

	
		@PostMapping("/withdraw")
		public void withdraw(@RequestBody Map<String, Object> request) {
			long fromAccountNumber = Long.parseLong(request.get("fromAccountNumber").toString());
			double amount = Double.parseDouble(request.get("amount").toString());
			TransactionType transactionType = transactiontyservice.getTransactionTypeById(1); 

			accountservice.withdraw(fromAccountNumber, amount, transactionType);
		}

		@PostMapping("/deposit")
		public void deposit(@RequestBody Map<String, Object> request) {
			long toAccountNumber = Long.parseLong(request.get("toAccountNumber").toString());
			double amount = Double.parseDouble(request.get("amount").toString());
			TransactionType transactionType = transactiontyservice.getTransactionTypeById(2); 

			accountservice.deposit(toAccountNumber, amount, transactionType);
		}

		@PostMapping("/transfer")
		public void transfer(@RequestBody Map<String, Object> request) {
			long fromAccountNumber = Long.parseLong(request.get("fromAccountNumber").toString());
			long toAccountNumber = Long.parseLong(request.get("toAccountNumber").toString());
			double amount = Double.parseDouble(request.get("amount").toString());
			TransactionType transactionType = transactiontyservice.getTransactionTypeById(3);

			accountservice.transfer(fromAccountNumber, toAccountNumber, amount, transactionType);
		}
		
		  @GetMapping("/customerdetails")
		    public List<AccountWithCustomerDTO> getAccountsWithCustomerDetailsByCustomerFirstName(
		            @RequestParam String firstName) {
		        return accountservice.getAccountsWithCustomerDetailsByCustomerFirstName(firstName);
		    }
		  
		  @GetMapping("/customerAccount/{customerId}")
		  public List<Integer> getAccountNumbersByCustomerId(@PathVariable int customerId) {
		        return accountservice.getAccountNumbersByCustomerId(customerId);
		    }
		  
		  @PostMapping("/Addaccounts")
		  public Account createAccount(@RequestBody AccountRequest request) {
			  Customer customer = customerService.getCustomerById(request.getCustomer_id());
			    Bank bank = bankService.getBankById(request.getBankId());
			    double balance = request.getBalance();

			    return accountservice.createAccount(customer, bank, balance);
		    }
}

