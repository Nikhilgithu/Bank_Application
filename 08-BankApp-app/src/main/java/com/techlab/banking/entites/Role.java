package com.techlab.banking.entites;



import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="role")
@RequiredArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
public class Role {

	@Id
	@Column
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private int roleid;

    @Column
    private String rolename;
    
    @ManyToMany(mappedBy = "roles")
    private List<Customer> customers;
    
   
}
