package com.junior.banking.entities;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Transaction extends AbstractEntity{
	
	private BigDecimal amount;
	
	@Enumerated(EnumType.STRING)
	private TransactionType type; 
	
	private String destinationIban;
	
	@Column(updatable = false)
	private LocalDate transactionDate;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	
	
	
	
}
