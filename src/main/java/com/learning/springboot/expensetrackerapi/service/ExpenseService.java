package com.learning.springboot.expensetrackerapi.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.learning.springboot.expensetrackerapi.dto.ExpenseDto;
import com.learning.springboot.expensetrackerapi.exception.ExpenseNotFoundException;
import com.learning.springboot.expensetrackerapi.model.Expense;
import com.learning.springboot.expensetrackerapi.repository.ExpenseRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ExpenseService {
	private final ExpenseRepository expenseRepository;

	public String addExpense(ExpenseDto expenseDto) {
		Expense expense = mapFromDto(expenseDto);
		return expenseRepository.insert(expense).getId();
	}
	
	public void updateExpense(ExpenseDto expenseDto) {
		Expense savedExpense = expenseRepository.findById(expenseDto.getId())
				.orElseThrow(() -> new ExpenseNotFoundException(String.format("Cannot find expense by id %s", expenseDto.getId())));
		savedExpense.setExpenseName(expenseDto.getExpenseName());
		savedExpense.setExpenseCategory(expenseDto.getExpenseCategory());
		savedExpense.setExpenseAmount(expenseDto.getExpenseAmount());
		
		expenseRepository.save(savedExpense);
	}
	
	public void deleteExpense(String id) {
		expenseRepository.deleteById(id);
	}

	public ExpenseDto getExpense(String name) {
		Expense expense = expenseRepository.findByName(name).orElseThrow(
				() -> new ExpenseNotFoundException(String.format("Cannot find expense by name - %s", name)));
		return mapToDto(expense);
	}
	
	public List<ExpenseDto> getAllExpense(){
		return expenseRepository.findAll()
				.stream().map(this::mapToDto).collect(Collectors.toList());
	}
	private ExpenseDto mapToDto(Expense expense) {
		return ExpenseDto.builder()
				.id(expense.getId())
				.expenseName(expense.getExpenseName())
				.expenseCategory(expense.getExpenseCategory())
				.expenseAmount(expense.getExpenseAmount()).build();
	}

	private Expense mapFromDto(ExpenseDto expenseDto) {
		return Expense.builder().expenseName(expenseDto.getExpenseName())
				.expenseCategory(expenseDto.getExpenseCategory()).expenseAmount(expenseDto.getExpenseAmount()).build();
	}
}
