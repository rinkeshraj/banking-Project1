package com.example.banking.service.impl;

import com.example.banking.dto.AccountDto;
import com.example.banking.entity.AccountEntity;
import com.example.banking.exception.CustomException;
import com.example.banking.mapper.AccountMapper;
import com.example.banking.repository.AccountRepository;
import com.example.banking.service.AccountService;
import com.example.banking.validator.AccountValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import static com.example.banking.constant.AccountConstant.ERROR_MSG_1;
import static com.example.banking.constant.AccountConstant.NOT_EXISTS;

@Slf4j
@Service
public class AccountServiceImpl implements AccountService  {

    private AccountRepository accountRepository;
    private AccountValidator accountValidator;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository, AccountValidator accountValidator) {
        this.accountRepository = accountRepository;
        this.accountValidator = accountValidator;
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
            log.info("Checking in DB");
            AccountEntity existRecord = accountRepository.findByPhoneNumber(accountDto.getPhoneNumber());
            if (existRecord == null) {
                log.info("Record not exists, Inserting the Record In DB");
                AccountEntity accountEntity = AccountMapper.mapToAccount(accountDto);
                AccountEntity saveAccount = accountRepository.save(accountEntity);
                log.info("Record Inserted in Account table with Id: {}", saveAccount.getId());
                return AccountMapper.mapToAccountDto(saveAccount);
            }else{
                log.error("Record already exists In DB with PhoneNumber: {}", accountDto.getPhoneNumber());
                throw new CustomException(HttpStatus.NOT_ACCEPTABLE, "NA001", HttpStatus.NOT_ACCEPTABLE.getReasonPhrase(), NOT_EXISTS);
            }
    }

    @Override
    public AccountDto getAccountDetailsByPhoneNumber(String phoneNumber) throws ResponseStatusException{
        AccountEntity existRecord = accountRepository.findByPhoneNumber(phoneNumber);
        log.info("Checking in DB with PhoneNumber: {}", phoneNumber);
        if(existRecord == null ){
            log.warn("Record Not found in DB");
            throw new CustomException(HttpStatus.NOT_FOUND, "NOT FOUND", HttpStatus.NOT_FOUND.getReasonPhrase(), ERROR_MSG_1);
        }else{
            log.info("Record Matched");
            return AccountMapper.mapToAccountDto(existRecord);
        }
    }

    @Override
    public AccountDto deposit(String phoneNumber, double amount){
        AccountEntity existRecord = accountRepository.findByPhoneNumber(phoneNumber);
        if(existRecord == null ){
            log.warn("Record Not found in DB with phoneNumber: {} to deposit", phoneNumber);
            throw new CustomException(HttpStatus.NOT_FOUND, "NOT FOUND", HttpStatus.NOT_FOUND.getReasonPhrase(), ERROR_MSG_1);
        }else{
            log.info("Record Found...");
            double totalBalance = existRecord.getBalance() + amount;
            log.info("Total Balance present in DB: {}",totalBalance);
            existRecord.setBalance(totalBalance);
            AccountEntity saveAccount = accountRepository.save(existRecord);
            log.info("Updating the Record for phoneNumber after deposit: {}",existRecord.getPhoneNumber());
            return AccountMapper.mapToAccountDto(saveAccount);
        }
    }

    @Override
    public AccountDto withDraw(String phoneNumber, double withdrawAmount){
        AccountEntity existRecord = accountRepository.findByPhoneNumber(phoneNumber);
        if(existRecord == null ){
            log.warn("Record Not found in DB with phoneNumber: {} to withdraw", phoneNumber);
            throw new CustomException(HttpStatus.NOT_FOUND, "NOT FOUND", HttpStatus.NOT_FOUND.getReasonPhrase(), ERROR_MSG_1);
        }else{
            log.info("Record Found....");
            double DbBalance = existRecord.getBalance();
            accountValidator.verifyBalanceInDB(DbBalance,withdrawAmount);
            log.info("Balance present: {} and Withdraw balance: {}",DbBalance,withdrawAmount);
            double remainingBalance = DbBalance - withdrawAmount;
            log.info("Remaining Balance present in DB: {}",remainingBalance);
            existRecord.setBalance(remainingBalance);
            AccountEntity saveAccount = accountRepository.save(existRecord);
            log.info("Updating the Record for phoneNumber after withdrawal: {}",existRecord.getPhoneNumber());
            return AccountMapper.mapToAccountDto(saveAccount);
        }
    }
}
