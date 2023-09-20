package com.junior.banking.entities;

import java.util.List;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "USER")
public class User extends AbstractEntity{

	
	/**
	 * 
	 */

	private String firstname;
	
	private String lastname;
	
	@Column(unique = true)
	private String email;
	
	private String password;
	
	@OneToOne
	private Address address;
	
	private boolean active;
	
	@OneToMany(mappedBy = "user")
	private List<Transaction> transactions;
	
	@OneToMany(mappedBy = "user")
	private List<Contact> contacts;
	
	@OneToOne
	private Account account;
	
	@OneToOne
	private Role role;

}
