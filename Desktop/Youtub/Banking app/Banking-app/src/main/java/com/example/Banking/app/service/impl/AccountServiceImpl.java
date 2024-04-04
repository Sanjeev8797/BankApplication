package com.example.Banking.app.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.Banking.app.dto.AccountDto;
import com.example.Banking.app.entity.Account;
import com.example.Banking.app.mapper.AccountMapper;
import com.example.Banking.app.repositry.AccountReposity;
import com.example.Banking.app.service.AccountService;
@Service
public class AccountServiceImpl implements AccountService {

	private AccountReposity accountRepositry;

	AccountServiceImpl(AccountReposity accountRepositry) {
		this.accountRepositry = accountRepositry;
	}

	@Override
	public AccountDto createAccount(AccountDto accountDto) {
		Account account = AccountMapper.mapToAccount(accountDto);
		Account saveAccount = accountRepositry.save(account);

		return AccountMapper.mapToAccountDto(saveAccount);
	}

	@Override
	public AccountDto getAccountById(Long id) {
		Account account=accountRepositry.findById(id).orElseThrow(()->new RuntimeException("Account does not exists"));
		return AccountMapper.mapToAccountDto(account);
	}

	@Override
	public AccountDto deposit(Long id, double amount) {
		Account account=accountRepositry.findById(id).orElseThrow(()->new RuntimeException("Account does not exists"));
		double total=account.getBalance()+amount;
		account.setBalance(total);
		Account saveAccount=accountRepositry.save(account);
		return AccountMapper.mapToAccountDto(saveAccount) ;
	}

	@Override
	public AccountDto withdrow(Long id, double amount) {
		Account account=accountRepositry.findById(id).orElseThrow(()->new RuntimeException("Account does nt exists"));
		if(account.getBalance()<amount) {
			throw new RuntimeException("Insufficient balance");
		}
		double total=account.getBalance()-amount;
		account.setBalance(total);
		Account saveAccount=accountRepositry.save(account);
		return AccountMapper.mapToAccountDto(saveAccount) ;
	}

	@Override
	public List<AccountDto> getAllAccounts() {
		List<Account> accounts=accountRepositry.findAll();
		return accounts.stream().map((account)->AccountMapper.mapToAccountDto(account)).collect(Collectors.toList());
	}

	@Override
	public void deleteAccount(Long id) {
		accountRepositry.findById(id).orElseThrow(()-> new RuntimeException("Account does not exists"));
	accountRepositry.deleteById(id);
		
		
	}


	

}
