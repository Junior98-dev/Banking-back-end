package com.junior.banking.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.junior.banking.entities.Role;

public interface Rolerepository extends JpaRepository<Role, Integer>{

	Optional<Role> findByName(String roleName);
	
}
