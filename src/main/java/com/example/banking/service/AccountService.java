package com.example.banking.service;

import com.example.banking.dto.AccountDto;

public interface AccountService {

    AccountDto createAccount(AccountDto accountDto);

    AccountDto getAccountDetailsByPhoneNumber(String phoneNumber);

    AccountDto deposit(String phoneNumber, double amount);

    AccountDto withDraw(String phoneNumber, double amount);
}
