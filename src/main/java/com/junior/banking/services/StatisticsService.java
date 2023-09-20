package com.junior.banking.services;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.junior.banking.dto.TransactionSumDetails;

public interface StatisticsService {

	// les Map sont des listes qui acceptent une clé et une valeur
	// Méthode qui permet d'afficher la liste des transactions éffectuées dans une intervale de date
	List<TransactionSumDetails> findSumTranctionsByDate(LocalDate startDate, LocalDate endDate, Integer userId);
	
	// le solde de compte de l'utilisateur
	BigDecimal getAccountBalance(Integer userId);
	
	// Montant le plus élèvé transféré
	BigDecimal highesTransfert(Integer userId);
	
	BigDecimal highesDeposit(Integer userId);
	
}
