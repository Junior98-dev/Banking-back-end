package com.junior.banking.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.junior.banking.entities.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {

}
