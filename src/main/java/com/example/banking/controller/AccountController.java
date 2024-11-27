package com.example.banking.controller;

import com.example.banking.dto.AccountDto;
import com.example.banking.service.AccountService;
import com.example.banking.validator.AccountValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import static com.example.banking.constant.AccountConstant.*;

@Slf4j
@RestController
@RequestMapping(BASIC_URL)
public class AccountController {

    private AccountService accountService;
    private AccountValidator accountValidator;

    @Autowired
    public AccountController(AccountService accountService, AccountValidator accountValidator) {
        this.accountService = accountService;
        this.accountValidator = accountValidator;
    }


    //For creating record in DB
    @PostMapping(value = CREATE)
    public ResponseEntity<AccountDto> createAccountRecords(@RequestBody AccountDto accountDto){
        log.info("Request received for Create");
        AccountDto accountDto1= accountService.createAccount(accountDto);
        return new ResponseEntity<>(accountDto1,HttpStatus.CREATED);
    }

    //Get the Account Details
    @GetMapping(value = GET_ACCOUNT)
    public ResponseEntity<AccountDto> getAccountDetails(@PathVariable String phoneNumber){
        log.info("Get Request received");
        AccountDto accountDto = accountService.getAccountDetailsByPhoneNumber(phoneNumber);
        return new ResponseEntity<>(accountDto,HttpStatus.OK);
    }

    //Get the Account Deposit
    @PostMapping(value = DEPOSIT_AMOUNT)
    public ResponseEntity<String> getAccountDetails(@RequestBody AccountDto request){
        log.info("Post Request received to Deposit the Balance");
        double balance = request.getBalance();
        String phoneNumber = request.getPhoneNumber();
        accountValidator.verifyRequestData(phoneNumber, balance);
        log.info("Amount to Deposit: {}", balance);
        AccountDto accountDto = accountService.deposit(phoneNumber, balance);
        return new ResponseEntity<>("Successfully Deposit",HttpStatus.OK);
    }

    //Get the Account Withdraw
    @PostMapping(value = WITH_DRAW_AMOUNT)
    public ResponseEntity<String> amountWithdraw(@RequestBody AccountDto request){
        log.info("Post Request received to WithDraw the Balance");
        double balance = request.getBalance();
        String phoneNumber = request.getPhoneNumber();
        accountValidator.verifyRequestData(phoneNumber, balance);
        log.info("Amount to WithDraw: {}", balance);
        AccountDto accountDto = accountService.withDraw(phoneNumber, balance);
        return new ResponseEntity<>("Successfully withDraw",HttpStatus.OK);
    }
}
