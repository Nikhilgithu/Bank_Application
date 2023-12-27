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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
	
	@Entity
	@Table(name="bank")
	@Getter
	@Setter
	@RequiredArgsConstructor
	@AllArgsConstructor
	@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
	public class Bank {
	
		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column
	    private int bankId;
	
	    @Column
	    private String bankName;
	
	    @JsonIgnore
	    @OneToMany(mappedBy = "bank", cascade = CascadeType.ALL)
	    private List<Account> accounts;
	}
