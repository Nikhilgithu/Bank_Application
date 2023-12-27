package com.techlab.banking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techlab.banking.entites.TransactionType;

public interface TransactionTypeRepository extends JpaRepository<TransactionType,Integer>{

}
