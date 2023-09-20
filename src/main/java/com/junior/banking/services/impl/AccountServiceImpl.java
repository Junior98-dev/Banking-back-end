package com.junior.banking.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.iban4j.CountryCode;
import org.iban4j.Iban;
import org.springframework.stereotype.Service;

import com.junior.banking.dto.AccountDto;
import com.junior.banking.entities.Account;
import com.junior.banking.exceptions.OperationNomPermittedException;
import com.junior.banking.repositories.AccountRepository;
import com.junior.banking.services.AccountService;
import com.junior.banking.validators.ObjectsValidator;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService{

	private final AccountRepository accountRepository;
	private final ObjectsValidator<AccountDto> validator;
	
	@Override
	public Integer save(AccountDto dto) {
		// bloque la modification des compte -> iban ne peut pas changer
		/*if(dto.getId() != null) {
			throw new OperationNomPermittedException(
					"Account cannot be updated",
					"save account",
					"Account",
					"Update not permitted"
			);
		}*/
		validator.validate(dto);
		Account account = AccountDto.toEntity(dto);
		boolean userHastAlreadyAnAccount = accountRepository.findByUserId(account.getUser().getId()).isPresent();
		if(userHastAlreadyAnAccount && account.getUser().isActive()) {
			throw new OperationNomPermittedException(
					"l'utilisateur sélectionné a déja un compte actif", 
					"créer un compte", 
					"Account service", 
					"Account creation"
			);
		}
		//généré un IBAN
		if(dto.getId() == null) {
			account.setIban(generateRandomIban());
		}
		return accountRepository.save(account).getId();
	}

	@Override
	public List<AccountDto> findAll() {
		return accountRepository.findAll()
				.stream()
				.map(AccountDto::fromEntity)
				.collect(Collectors.toList());
	}

	@Override
	public AccountDto findById(Integer id) {
		return accountRepository.findById(id)
				.map(AccountDto::fromEntity)
				.orElseThrow(() -> new EntityNotFoundException("No account was found width the ID : " + id));
	}

	@Override
	public void delecte(Integer id) {
		accountRepository.deleteById(id);
	}
	
	private String generateRandomIban() {
		
		// généré un iban
		String iban = Iban.random(CountryCode.DE).toFormattedString();
		// verifier s'il existe
		boolean ibanExist = accountRepository.findByIban(iban).isPresent();
		//s'il existe généré un nouveau iban
		if(ibanExist) {
			generateRandomIban();
		}
		
		//s'il existe pas return iban
		return iban;
	}

}
