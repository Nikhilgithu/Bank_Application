package com.techlab.banking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techlab.banking.entites.Bank;
import com.techlab.banking.repository.BankRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BankServiceImpl implements BankService {

	@Autowired
	private BankRepository bankRepo;

	@Override
	public void saveBank(Bank bank) {

		bankRepo.save(bank);
	}
	 @Override
	    public Bank getBankById(int bankId) {
	        return bankRepo.findById(bankId).orElse(null);
	    }
	@Override
	public List<Bank> getAllbank() {
		return bankRepo.findAll();
	}
	@Override
	public void deleteBank(int bankId) {
		bankRepo.deleteById(bankId);
		
	}
	
	@Override
	public void updateBankName(int bankId, String newBankName) {
	    Bank bankToUpdate = bankRepo.findById(bankId).orElse(null);
	    if (bankToUpdate != null) {
	        bankToUpdate.setBankName(newBankName);
	        bankRepo.save(bankToUpdate);
	    }
	}
	
}
