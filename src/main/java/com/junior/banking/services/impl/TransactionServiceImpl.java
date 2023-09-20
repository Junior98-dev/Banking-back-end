package com.junior.banking.services.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.junior.banking.dto.TransactionDto;
import com.junior.banking.entities.Transaction;
import com.junior.banking.entities.TransactionType;
import com.junior.banking.repositories.TransactionRepository;
import com.junior.banking.services.TransactionService;
import com.junior.banking.validators.ObjectsValidator;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService{

	private final TransactionRepository transactionRepository;
	private final ObjectsValidator<TransactionDto> validator;
	
	@Override
	public Integer save(TransactionDto dto) {

		validator.validate(dto);
		Transaction transaction = TransactionDto.toEntity(dto);
		BigDecimal transactionMultiplier = BigDecimal.valueOf(getTransactionMultiplier(transaction.getType()));
		BigDecimal amount = transaction.getAmount().multiply(transactionMultiplier);
		transaction.setAmount(amount);
		return transactionRepository.save(transaction).getId();
	}

	@Override
	public List<TransactionDto> findAll() {
		return transactionRepository.findAll()
				.stream()
				.map(TransactionDto::fromEntity)
				.collect(Collectors.toList());
	}

	@Override
	public TransactionDto findById(Integer id) {
		return transactionRepository.findById(id)
				.map(TransactionDto::fromEntity)
				.orElseThrow(() -> new EntityNotFoundException("No transaction was found with the ID"));
		
	}

	@Override
	public void delecte(Integer id) {
		transactionRepository.deleteById(id);
	}
	
	private int getTransactionMultiplier(TransactionType type) {		
		return TransactionType.Transfert == type ? -1 : 1;
	}

	@Override
	public List<TransactionDto> findAllUserId(Integer userId) {
		
		return transactionRepository.findByUserId(userId)
				.stream()
				.map(TransactionDto::fromEntity)
				.collect(Collectors.toList());
	}

}
