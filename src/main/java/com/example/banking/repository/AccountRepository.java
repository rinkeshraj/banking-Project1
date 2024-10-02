package com.example.banking.repository;

import com.example.banking.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AccountRepository extends JpaRepository<AccountEntity,Long> {
    AccountEntity findByPhoneNumber(String phoneNumber);

}
