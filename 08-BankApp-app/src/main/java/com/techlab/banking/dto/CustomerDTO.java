package com.techlab.banking.dto;

import com.techlab.banking.entites.Account;
import com.techlab.banking.entites.Role;
import com.techlab.banking.entites.userdetail;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@RequiredArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class CustomerDTO {

    private int customerId;
    private String firstName;
    private String lastName;
    private List<Account> accounts;
    private userdetail userDetail;
    private List<Role> roles;

    // Constructors, getters, and setters
}
