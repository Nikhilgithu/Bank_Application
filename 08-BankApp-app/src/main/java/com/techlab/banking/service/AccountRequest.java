package com.techlab.banking.service;

import java.util.List;

import com.techlab.banking.entites.Bank;
import com.techlab.banking.entites.Customer;
import com.techlab.banking.entites.Transaction;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class AccountRequest {

	private int customer_id;
	private int bankId;
	private double balance;
	
	 
}
