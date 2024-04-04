package com.example.Banking.app.repositry;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Banking.app.entity.Account;

public interface AccountReposity extends JpaRepository<Account, Long>{

}
