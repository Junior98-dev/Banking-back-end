package com.junior.banking.services;

import java.util.List;

import com.junior.banking.dto.TransactionDto;

public interface TransactionService extends AbstractService<TransactionDto>{

	List<TransactionDto> findAllUserId(Integer userId);
}
