package com.junior.banking.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.junior.banking.entities.Account;

public interface AccountRepository extends JpaRepository<Account, Integer> {

	Optional<Account> findByIban(String iban);

	Optional<Account> findByUserId(Integer id);

}
