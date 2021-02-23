package com.learning.springboot.expensetrackerapi.model;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document("expense")
public class Expense {
	@Id
	private String id;
	@Field("name")
	private String expenseName;
	@Field("category")
	private ExpenseCategory expenseCategory;
	@Field("amount")
	private BigDecimal expenseAmount;
}
