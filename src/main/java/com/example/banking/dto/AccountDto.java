package com.example.banking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AccountDto {
    private Long id;
    private String holderFirstName;
    private String holderMiddleName;
    private String holderLastName;
    private String email;
    private String phoneNumber;
    private String balance;

}
