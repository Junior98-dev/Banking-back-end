package com.junior.banking.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.junior.banking.dto.AddressDto;
import com.junior.banking.entities.Address;
import com.junior.banking.repositories.AddressRepository;
import com.junior.banking.services.addressService;
import com.junior.banking.validators.ObjectsValidator;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements addressService{

	private final AddressRepository addressRepository; 
	private final ObjectsValidator<AddressDto> validator;
	
	@Override
	public Integer save(AddressDto dto) {
		validator.validate(dto);
		Address address = AddressDto.toEntity(dto);
		return addressRepository.save(address).getId();
	}

	@Override
	public List<AddressDto> findAll() {
		return addressRepository.findAll()
				.stream()
				.map(AddressDto::fromEntity)
				.collect(Collectors.toList());
	}

	@Override
	public AddressDto findById(Integer id) {
		return addressRepository.findById(id)
				.map(AddressDto::fromEntity)
				.orElseThrow(()-> new EntityNotFoundException("No address found width the ID : " + id));                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              
	}

	@Override
	public void delecte(Integer id) {
		addressRepository.deleteById(id);
	}

}
