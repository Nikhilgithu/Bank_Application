package com.techlab.banking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techlab.banking.entites.Bank;

public interface BankRepository extends JpaRepository<Bank,Integer>{

}
