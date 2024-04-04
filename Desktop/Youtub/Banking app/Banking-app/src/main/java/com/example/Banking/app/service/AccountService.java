package com.example.Banking.app.service;

import java.util.List;

import com.example.Banking.app.dto.AccountDto;

public interface AccountService {
AccountDto createAccount(AccountDto accountDto);
AccountDto getAccountById(Long id);
AccountDto deposit(Long id,double amount);
AccountDto withdrow(Long id,double amount);
List<AccountDto> getAllAccounts();
void deleteAccount(Long id); 

}
