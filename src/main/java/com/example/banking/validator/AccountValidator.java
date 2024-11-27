package com.example.banking.validator;

import com.example.banking.exception.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import static com.example.banking.constant.AccountConstant.AMOUNT_NOT_PRESENT;
import static com.example.banking.constant.AccountConstant.PHONE_NUMBER_NOT_PRESENT;

@Slf4j
@Component
public class AccountValidator {

    public void verifyRequestData(String phoneNumber,String amount){
        if (phoneNumber.isBlank() && phoneNumber.isEmpty()) {
            log.warn("Request Body should contain phoneNumber");
            throw new CustomException(HttpStatus.BAD_REQUEST, "BAD REQUEST", HttpStatus.BAD_REQUEST.getReasonPhrase(), PHONE_NUMBER_NOT_PRESENT);
        }
        if (String.valueOf(amount).isEmpty() && String.valueOf(amount).isBlank()){
            log.warn("Request Body should contain Amount");
            throw new CustomException(HttpStatus.BAD_REQUEST, "BAD REQUEST", HttpStatus.BAD_REQUEST.getReasonPhrase(), AMOUNT_NOT_PRESENT);
        }
        else{
            log.info("Amount: {}", amount);
            log.info("Phone Number: {}", phoneNumber);
        }
    }
}
