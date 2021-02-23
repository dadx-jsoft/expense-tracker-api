package com.learning.springboot.expensetrackerapi.api;

import java.net.URI;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.learning.springboot.expensetrackerapi.dto.ExpenseDto;
import com.learning.springboot.expensetrackerapi.service.ExpenseService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/expense")
@RequiredArgsConstructor
public class ExpenseController {
	private final ExpenseService expenseService;
	
	@PostMapping
	public ResponseEntity<Void> addExpense(@RequestBody ExpenseDto expenseDto){
		String expenseId = expenseService.addExpense(expenseDto);
		URI location = ServletUriComponentsBuilder
						.fromCurrentRequest().path("/{id}").buildAndExpand(expenseId).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping
	@ResponseStatus(HttpStatus.OK)
	public void updateExpense(@RequestBody ExpenseDto expenseDto) {
		expenseService.updateExpense(expenseDto);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteExpense(@PathVariable String id) {
		expenseService.deleteExpense(id);
	}
	
	@GetMapping("/{name}")
	@ResponseStatus(HttpStatus.OK)
	public ExpenseDto getExpenseByName(@PathVariable String name) {
		return expenseService.getExpense(name);
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<ExpenseDto> getAllExpenses() {
		return expenseService.getAllExpense();
	}
}
