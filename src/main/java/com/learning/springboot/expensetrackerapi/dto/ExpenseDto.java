package com.learning.springboot.expensetrackerapi.dto;

import java.math.BigDecimal;

import com.learning.springboot.expensetrackerapi.model.ExpenseCategory;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExpenseDto {
	private String id;
	private String expenseName;
	private ExpenseCategory expenseCategory;
	private BigDecimal expenseAmount;
}
