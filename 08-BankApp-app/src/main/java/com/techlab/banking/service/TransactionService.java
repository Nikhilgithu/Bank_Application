package com.techlab.banking.service;

import java.util.List;

import com.techlab.banking.dto.TransactionDTO;
import com.techlab.banking.entites.Transaction;

public interface TransactionService {

	 void addTransaction(Transaction transaction);
	 //List<TransactionDTO> GetAllTransactions();
	 List<TransactionDTO> getTransactionsByAccountNumber(int accountNumber);
	 
}
