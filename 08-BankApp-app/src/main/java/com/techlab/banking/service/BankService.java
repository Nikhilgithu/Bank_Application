package com.techlab.banking.service;

import java.util.List;

import com.techlab.banking.entites.Bank;

public interface BankService {

	void saveBank(Bank bank);
	Bank getBankById(int bankId);
	List<Bank> getAllbank();
	void deleteBank(int bankId);
	void updateBankName(int bankId, String newBankName);

}
