package com.techlab.banking.dto;

import java.sql.Date;

import com.techlab.banking.entites.Account;
import com.techlab.banking.entites.TransactionType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class TransactionDTO {

	private int transactionId;
	//private TransactionType transactiontype;
	private Date date;
	private double amount;
	private int senderAccount;
	private int receiverAccount;
}
