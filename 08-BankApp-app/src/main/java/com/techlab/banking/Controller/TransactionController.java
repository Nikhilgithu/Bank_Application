package com.techlab.banking.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techlab.banking.dto.TransactionDTO;
import com.techlab.banking.entites.Transaction;
import com.techlab.banking.service.TransactionService;

import lombok.RequiredArgsConstructor;
@CrossOrigin(origins="*")
@RestController
@RequestMapping("/BankApp")
@RequiredArgsConstructor
public class TransactionController {
	
	
	@Autowired
    private  TransactionService transactionService;

	@PostMapping("/addTransaction")
    public void addTransaction(@RequestBody Transaction transaction) {
        transactionService.addTransaction(transaction);
    }
//	@GetMapping("/getAllTransaction")
//	public List<TransactionDTO> GetAllTransactions()
//	{
//		return transactionService.GetAllTransactions();
//	}
//	
	@GetMapping("/transactions/{accountNumber}")
	public List<TransactionDTO> getTransactionsByAccountNumber(@PathVariable int accountNumber) {
        return transactionService.getTransactionsByAccountNumber(accountNumber);
    }
}
