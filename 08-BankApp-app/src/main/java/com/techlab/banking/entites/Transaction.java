package com.techlab.banking.entites;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="transaction")
@RequiredArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
public class Transaction {

	@Id
	@Column
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int transactionId;

	@ManyToOne(cascade= {CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH})
    @JoinColumn(name = "transactiontype_id")
    private TransactionType transactiontype;

	@Column
	private Date date;

	@Column
	private double amount;

	@ManyToOne(cascade= {CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH})
	@JoinColumn(name = "sender_account_number")
	private Account senderAccount;

	@ManyToOne(cascade= {CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH})
	@JoinColumn(name = "receiver_account_number")
	private Account receiverAccount;

}
