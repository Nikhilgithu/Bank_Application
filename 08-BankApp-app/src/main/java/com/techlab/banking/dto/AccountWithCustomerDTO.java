package com.techlab.banking.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class AccountWithCustomerDTO {
	private int accountno;
    private double balance;
    private int customerId;
    private String bankName;
    

}