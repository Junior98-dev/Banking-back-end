package com.junior.banking.entities;

import java.util.Collection;
import java.util.Collections;
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
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "USER")
public class User extends AbstractEntity implements UserDetails {

	
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

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority(role.getName()));
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return active;
	}
}
