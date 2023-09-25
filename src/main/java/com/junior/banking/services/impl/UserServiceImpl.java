package com.junior.banking.services.impl;

import java.util.List;
import java.util.stream.Collectors;


import com.junior.banking.config.JwtUtils;
import com.junior.banking.dto.AuthenticationRequest;
import com.junior.banking.dto.AuthenticationResponse;
import com.junior.banking.entities.Role;
import com.junior.banking.repositories.RoleRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
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

	private static final String ROLE_USER = "ROLE_USER";

	private final UserRepository repository;
	private final AccountService accountService;
	private final ObjectsValidator<UserDto> validator;
	private final PasswordEncoder passwordEncoder;
	private final JwtUtils jwtUtils;
	private final AuthenticationManager authManger;
	private final RoleRepository  roleRepository;


	
	@Override
	public Integer save(UserDto dto) {
		validator.validate(dto);
		User user = UserDto.toEntity(dto);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return repository.save(user).getId();
		
		
	}

	@Override
	@Transactional
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

	@Override
	@Transactional
	public AuthenticationResponse register(UserDto dto) {
		validator.validate(dto);
		User user = UserDto.toEntity(dto);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setRole(findOrCreateRole(ROLE_USER));
		repository.save(user);
		var jwtToken = jwtUtils.generateToken(user);
		return AuthenticationResponse.builder()
				.token(jwtToken)
				.build();
	}

	@Override
	public AuthenticationResponse authenticate(AuthenticationRequest request) {
		authManger.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
		var user = repository.findByEmail(request.getEmail())
				.orElseThrow();
		var jwtToken = jwtUtils.generateToken(user);
		return AuthenticationResponse.builder()
				.token(jwtToken)
				.build();

	}

	private Role findOrCreateRole(String roleName) {
		Role role = roleRepository.findByName(roleName)
				.orElse(null);
		if (role == null) {
			return roleRepository.save(
					Role.builder()
							.name(roleName)
							.build()
			);
		}
		return role;
	}


}
