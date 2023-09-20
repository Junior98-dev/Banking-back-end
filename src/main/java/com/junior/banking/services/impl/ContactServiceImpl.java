package com.junior.banking.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.junior.banking.dto.ContactDto;
import com.junior.banking.entities.Contact;
import com.junior.banking.repositories.ContactRepository;
import com.junior.banking.services.ContactService;
import com.junior.banking.validators.ObjectsValidator;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService{

	private final ContactRepository contactRepository;
	private final ObjectsValidator<ContactDto> validator;
	
	@Override
	public Integer save(ContactDto dto) {
		validator.validate(dto);
		Contact contact = ContactDto.toEntity(dto);
		return contactRepository.save(contact).getId();
	}

	@Override
	public List<ContactDto> findAll() {
		return contactRepository.findAll()
				.stream()
				.map(ContactDto::fromEntity)
				.collect(Collectors.toList());
	}

	@Override
	public ContactDto findById(Integer id) {
		return contactRepository.findById(id)
				.map(ContactDto::fromEntity)
				.orElseThrow(() -> new EntityNotFoundException("No contact was found with the ID"));
	}

	@Override
	public void delecte(Integer id) {
		contactRepository.deleteById(id);
	}

	@Override
	public List<ContactDto> findAllByUserId(Integer userId) {
		return contactRepository.findByUserId(userId)
				.stream()
				.map(ContactDto::fromEntity)
				.collect(Collectors.toList());
	}

}
