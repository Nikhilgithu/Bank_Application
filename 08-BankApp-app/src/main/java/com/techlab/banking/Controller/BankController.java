package com.techlab.banking.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techlab.banking.entites.Bank;
import com.techlab.banking.service.BankService;
@CrossOrigin(origins="*")
@RestController
@RequestMapping("/Bankapp")
public class BankController {

	@Autowired
	private BankService bankservice;
	
	 @PostMapping("/Addbanks")
	    public void addBank(@RequestBody Bank bank) {
		 bankservice.saveBank(bank);
	    }
	 @GetMapping("/getAllBank")
	 public List<Bank> getAllbank()
	 {
		return bankservice.getAllbank();
	 }
	 
	 @DeleteMapping("/deleteBank/{bankId}")
	    public ResponseEntity<String> deleteBank(@PathVariable int bankId) {
	        Bank bank = bankservice.getBankById(bankId);
	        if (bank != null) {
	        	bankservice.deleteBank(bankId);
	            return new ResponseEntity<>("Bank deleted successfully", HttpStatus.OK);
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    }
	 
	 @PutMapping("/updateBankName/{bankId}")
	 public ResponseEntity<String> updateBankName( @PathVariable int bankId, @RequestBody String newBankName) {

	     Bank bankToUpdate = bankservice.getBankById(bankId);
	     if (bankToUpdate != null) {
	         bankToUpdate.setBankName(newBankName);
	         bankservice.saveBank(bankToUpdate);

	         return new ResponseEntity<>("Bank name updated successfully", HttpStatus.OK);
	     } else {
	         return ResponseEntity.notFound().build();
	     }
	 }


	 
}
