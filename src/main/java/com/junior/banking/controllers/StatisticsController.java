package com.junior.banking.controllers;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.junior.banking.dto.TransactionSumDetails;
import com.junior.banking.services.StatisticsService;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/statistics")
@RequiredArgsConstructor
public class StatisticsController {

	private final StatisticsService service; 
	
		@GetMapping("/sum-by-date/{user-id}")
		public ResponseEntity<List<TransactionSumDetails> > findSumTranctionsByDate(
				@PathVariable("user-id") Integer userId,
				@RequestParam("start-date")	@DateTimeFormat(pattern = "yyy-MM-dd") LocalDate startDate, 
				@RequestParam("end-date")	@DateTimeFormat(pattern = "yyy-MM-dd") LocalDate endDate
				){ 
			return ResponseEntity.ok(service.findSumTranctionsByDate(startDate, endDate, userId));
		}
	
		@GetMapping("/account-balance/{user-id}")
		public ResponseEntity<BigDecimal> getAccountBalance(@PathVariable("user-id") Integer userId){
			return ResponseEntity.ok(service.getAccountBalance(userId));
		};
		
		@GetMapping("/highest-transfert/{user-id}")
		public ResponseEntity<BigDecimal> highesTransfert(@PathVariable("user-id") Integer userId){
			return ResponseEntity.ok(service.highesTransfert(userId));
		};
		
		@GetMapping("/highest-deposit/{user-id}")
		public ResponseEntity<BigDecimal> highesDeposit(@PathVariable("user-id") Integer userId){
			return ResponseEntity.ok(service.highesDeposit(userId));
		};
}
