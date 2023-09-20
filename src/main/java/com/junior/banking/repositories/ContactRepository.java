package com.junior.banking.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.junior.banking.entities.Contact;

public interface ContactRepository extends JpaRepository<Contact, Integer> {

	List<Contact> findByUserId(Integer userId);

}
