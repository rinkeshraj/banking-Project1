package com.example.banking.controller;

import com.example.banking.dto.AccountDto;
import com.example.banking.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.example.banking.constant.AccountConstant.*;

@Slf4j
@RestController
@RequestMapping(BASIC_URL)
public class AccountController {

    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
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

}
