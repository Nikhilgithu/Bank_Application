package com.techlab.banking.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techlab.banking.entites.TransactionType;
import com.techlab.banking.service.TransactionTypeService;

import lombok.RequiredArgsConstructor;
@CrossOrigin(origins="*")
@RestController
@RequestMapping("/BankApp")
@RequiredArgsConstructor
public class transactiontypeController {
	
	@Autowired
	private TransactionTypeService transactiontyservice;

	@PostMapping("/addtransType")
	public void AddTransactionType(@RequestBody TransactionType transactiontype)
	{
		transactiontyservice.AddTransactionType(transactiontype);
	}
}
