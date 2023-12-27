package com.techlab.banking.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techlab.banking.dto.TransactionDTO;
import com.techlab.banking.entites.Transaction;
import com.techlab.banking.repository.TransactionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	private TransactionRepository transactionrepo;

	@Override
	public void addTransaction(Transaction transaction) {
		transactionrepo.save(transaction);
	}

//	@Override
//	public List<TransactionDTO> GetAllTransactions() {
//
//		List<Transaction> transaction = transactionrepo.findAll();
//		List<TransactionDTO> transactionDto = new ArrayList<>();
//		for (Transaction transactions : transaction) {
//			TransactionDTO transDto = new TransactionDTO();
//			transDto.setTransactionId(transactions.getTransactionId());
//			transDto.setAmount(transactions.getAmount());
//			transDto.setTransactiontype(transactions.getTransactiontype());
//			transDto.setDate(transactions.getDate());
//			transDto.setSenderAccount(transactions.getSenderAccount());
//			transDto.setReceiverAccount(transactions.getReceiverAccount());
//			transactionDto.add(transDto);
//
//		}
//		return transactionDto;
//	}

	@Override
    public List<TransactionDTO> getTransactionsByAccountNumber(int accountNumber) {
        List<Transaction> transactions = transactionrepo.findBySenderAccount_AccountNumberOrReceiverAccount_AccountNumber( accountNumber);

        // Convert Transaction entities to DTOs
        List<TransactionDTO> transactionDTOs = new ArrayList<>();
        for (Transaction transaction : transactions) {
            TransactionDTO transactionDTO = new TransactionDTO(
                    transaction.getTransactionId(),
                   // transaction.getTransactiontype(),
                    transaction.getDate(),
                    transaction.getAmount(),
                    transaction.getSenderAccount().getAccountNumber(),
                    transaction.getReceiverAccount().getAccountNumber()
            );
            transactionDTOs.add(transactionDTO);
        }

        return transactionDTOs;
    }
}
