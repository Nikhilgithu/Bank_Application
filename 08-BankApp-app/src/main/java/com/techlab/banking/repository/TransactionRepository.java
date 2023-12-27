package com.techlab.banking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.techlab.banking.entites.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

	@Query("SELECT t FROM Transaction t WHERE t.senderAccount.accountNumber = :accountNumber OR t.receiverAccount.accountNumber = :accountNumber")
	List<Transaction> findByAccountNumber(int accountNumber);

	@Query("SELECT t FROM Transaction t WHERE t.senderAccount.accountNumber = :accountNumber OR t.receiverAccount.accountNumber = :accountNumber")
	List<Transaction> findBySenderAccount_AccountNumberOrReceiverAccount_AccountNumber(int accountNumber);

}
