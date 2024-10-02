package com.example.banking.mapper;

import com.example.banking.dto.AccountDto;
import com.example.banking.entity.AccountEntity;

public class AccountMapper {

    public static AccountEntity mapToAccount(AccountDto accountDto){
        AccountEntity accountEntity= new AccountEntity(
                accountDto.getId(),
                accountDto.getHolderFirstName(),
                accountDto.getHolderMiddleName(),
                accountDto.getHolderLastName(),
                accountDto.getEmail(),
                accountDto.getPhoneNumber(),
                accountDto.getBalance()
        );
        return accountEntity;
    }

    public static AccountDto mapToAccountDto(AccountEntity accountEntity){
        AccountDto accountDto= new AccountDto(
                accountEntity.getId(),
                accountEntity.getHolderFirstName(),
                accountEntity.getHolderMiddleName(),
                accountEntity.getHolderLastName(),
                accountEntity.getEmail(),
                accountEntity.getPhoneNumber(),
                accountEntity.getBalance()
        );
        return accountDto;
    }
}
