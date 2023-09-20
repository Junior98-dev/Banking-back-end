package com.junior.banking.services.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.junior.banking.dto.TransactionSumDetails;
import com.junior.banking.entities.TransactionType;
import com.junior.banking.repositories.TransactionRepository;
import com.junior.banking.services.StatisticsService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StatisticsServiceImpl implements StatisticsService{

	private final TransactionRepository transactionRepository;

	@Override
	public BigDecimal getAccountBalance(Integer userId) {
		return transactionRepository.findAccountBalance(userId);
	}

	@Override
	public BigDecimal highesTransfert(Integer userId) {
		return transactionRepository.findHighestAmountByTransactionType(userId, TransactionType.Transfert);
	}

	@Override
	public BigDecimal highesDeposit(Integer userId) {
		return transactionRepository.findHighestAmountByTransactionType(userId, TransactionType.depot);
	}

	@Override
	public List<TransactionSumDetails> findSumTranctionsByDate(LocalDate startDate, LocalDate endDate, Integer userId) {
		LocalDateTime start = LocalDateTime.of(startDate, LocalTime.of(0, 0, 0));
		LocalDateTime end = LocalDateTime.of(endDate, LocalTime.of(23, 59, 59));
		return transactionRepository.findSumTransactionByDate(start, end, userId);
	}

}
