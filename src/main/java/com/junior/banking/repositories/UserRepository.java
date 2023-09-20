package com.junior.banking.repositories;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.junior.banking.entities.User;


public interface UserRepository extends JpaRepository<User, Integer>{

	  Optional<User> findByEmail(String email);

}
