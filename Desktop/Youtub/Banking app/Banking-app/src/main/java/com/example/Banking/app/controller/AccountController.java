package com.example.Banking.app.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Banking.app.dto.AccountDto;
import com.example.Banking.app.service.AccountService;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

	private AccountService accountService;

	public AccountController(AccountService accountService) {
		super();
		this.accountService = accountService;
	}

	// Add Account Rest Api
	@PostMapping
	public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto) {

		return new ResponseEntity<AccountDto>(accountService.createAccount(accountDto), HttpStatus.CREATED);
	}

	// get Account Rest APi
	@GetMapping("/{id}")
	public ResponseEntity<AccountDto> getAccountById(@PathVariable Long id) {
		AccountDto accountDto = accountService.getAccountById(id);
		return ResponseEntity.ok(accountDto);

	}
	// deposit Resp API

	@PutMapping("/{id}/deposit")
	public ResponseEntity<AccountDto> depost(@PathVariable Long id,@RequestBody Map<String, Double> request) {

		double amount = request.get("amount");

		AccountDto accountDto = accountService.deposit(id, amount);
		return ResponseEntity.ok(accountDto);
	}
	//Withdrow amount Rest Api
	@PutMapping("/{id}/withdrow")
	public ResponseEntity<AccountDto> withdrow(@PathVariable Long id,@RequestBody Map<String,Double> request){
		double amount=request.get("amount");
		AccountDto accountDto=accountService.deposit(id,amount );
		return ResponseEntity.ok(accountDto);
		
	}
	//GetAll Accout Rest Api
	@GetMapping
	public ResponseEntity<List<AccountDto>> getAllAcc(){
		List<AccountDto> accounts=accountService.getAllAccounts();
		return ResponseEntity.ok(accounts);
		}
	//Delete Account Rest API
	@DeleteMapping("/{id}/delete")
	public ResponseEntity<String> delete(@PathVariable Long id){
		accountService.deleteAccount(id);
		return ResponseEntity.ok(" Delete Success");
	}

}
