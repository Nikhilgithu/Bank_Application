package com.techlab.banking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techlab.banking.entites.TransactionType;
import com.techlab.banking.repository.TransactionTypeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransactionTypeImpl implements TransactionTypeService{

	@Autowired
	private TransactionTypeRepository transactiontyrepo;
	@Override
	public void AddTransactionType(TransactionType transactiontype) {
		transactiontyrepo.save(transactiontype);
		
	}
	 @Override
	    public TransactionType getTransactionTypeById(int id) {
	        return transactiontyrepo.findById(id).orElse(null);
	    }

}
