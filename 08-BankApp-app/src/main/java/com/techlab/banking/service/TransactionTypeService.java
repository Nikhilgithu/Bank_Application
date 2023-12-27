package com.techlab.banking.service;

import com.techlab.banking.entites.TransactionType;

public interface TransactionTypeService {

	void AddTransactionType(TransactionType transactiontype);
    TransactionType getTransactionTypeById(int id);

}
