package com.techlab.banking.entites;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "account")
@RequiredArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id")
public class Account {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_sequence")
	@SequenceGenerator(name = "account_sequence", sequenceName = "account_sequence", initialValue = 2001)
	private int accountNumber;

	@Column
	private double balance;

	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH })
	@JoinColumn(name = "customerId")
	private Customer customer;

	@JsonIgnore
	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH })
	@JoinColumn(name = "bankId")
	private Bank bank;

	@OneToMany(mappedBy = "senderAccount", cascade = CascadeType.ALL)
	private List<Transaction> senderTransactions;

	@OneToMany(mappedBy = "receiverAccount", cascade = CascadeType.ALL)
	private List<Transaction> receiverTransactions;

}
