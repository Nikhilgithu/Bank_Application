package com.techlab.banking.entites;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "customer")
@RequiredArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id")
public class Customer {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int customerId;

	@Column
	private String firstName;

	@Column
	private String lastName;

	@Column
	private String password;

	@JsonIgnore
	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
	private List<Account> accounts;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "uid")
	private userdetail userDetail;

	@ManyToMany(fetch = FetchType.EAGER) 
	@JoinTable(name = "customer_roles", joinColumns = @JoinColumn(name = "customer_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private List<Role> roles;


	@PrePersist
	public void updateUserDetail() {
		if (userDetail == null) {
			userDetail = new userdetail();
		}

		userDetail.setUsername(this.firstName);
		userDetail.setPassword(this.password);

		// Set the role to the userdetail
		userDetail.setRoles(this.roles);

	}

}
