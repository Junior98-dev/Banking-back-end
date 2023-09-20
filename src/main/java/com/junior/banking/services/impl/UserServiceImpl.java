package com.junior.banking.services.impl;

import java.util.List;
import java.util.stream.Collectors;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.junior.banking.dto.AccountDto;
import com.junior.banking.dto.UserDto;
import com.junior.banking.entities.User;
import com.junior.banking.repositories.UserRepository;
import com.junior.banking.services.AccountService;
import com.junior.banking.services.UserService;
import com.junior.banking.validators.ObjectsValidator;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

	private final UserRepository repository;
	private final AccountService accountService;
	private final ObjectsValidator<UserDto> validator;

	
	@Override
	public Integer save(UserDto dto) {
		validator.validate(dto);
		User user = UserDto.toEntity(dto);
		return repository.save(user).getId();
		
		
	}

	@Override
	public List<UserDto> findAll() {
		return repository.findAll()
				.stream()
				.map(UserDto::fromEntity)
				.collect(Collectors.toList());
	}

	@Override
	public UserDto findById(Integer id) {
		return repository.findById(id)
				.map(UserDto::fromEntity)
				.orElseThrow(() -> new EntityNotFoundException("No user was found width the provided ID : " + id));
	}

	@Override
	public void delecte(Integer id) {
		repository.deleteById(id);	
	}

	@Override
	@Transactional
	public Integer validateAccount(Integer id) {
		User user = repository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("aucun utilisateur n'a été trouvé pour la validation de ce compte"));
		
		
		// création du compte bancaire
		
		AccountDto account = AccountDto.builder()
				.user(UserDto.fromEntity(user))
				.build();
		accountService.save(account);
		
		user.setActive(true);
		repository.save(user);
		return user.getId();
	}

	@Override
	public Integer invalidateAccount(Integer id) {
		User user = repository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("aucun utilisateur n'a été trouvé pour la validation de ce compte"));
		user.setActive(false);
		repository.save(user);
		return user.getId();
	}


	
}
