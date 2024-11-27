package com.example.banking.validator;

import java.lang.String;
import com.example.banking.exception.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import static com.example.banking.constant.AccountConstant.AMOUNT_NOT_PRESENT;
import static com.example.banking.constant.AccountConstant.PHONE_NUMBER_NOT_PRESENT;
import static com.example.banking.constant.AccountConstant.INSUFFICIENT_AMOUNT;

@Slf4j
@Component
public class AccountValidator {

    public void verifyRequestData(String phoneNumber,double amount) throws CustomException{
        if (phoneNumber.isBlank() && phoneNumber.isEmpty()) {
            log.warn("Request Body should contain phoneNumber");
            throw new CustomException(HttpStatus.BAD_REQUEST, "400", HttpStatus.BAD_REQUEST.getReasonPhrase(), PHONE_NUMBER_NOT_PRESENT);
        }
        if (String.valueOf(amount).isEmpty() && String.valueOf(amount).isBlank()){
            log.warn("Request Body should contain Amount");
            throw new CustomException(HttpStatus.BAD_REQUEST, "400", HttpStatus.BAD_REQUEST.getReasonPhrase(), AMOUNT_NOT_PRESENT);
        }
        else{
            log.info("Phone Number: {}", phoneNumber);
        }
    }

    public void verifyBalanceInDB(double DbBalance, double withdrawAmount) throws CustomException {
        log.info("Checking DB balance with WithDraw Balance...");
        if (DbBalance < withdrawAmount){
            log.warn("Balance is less than withDraw balance");
            throw new CustomException(HttpStatus.UNPROCESSABLE_ENTITY, "422", HttpStatus.UNPROCESSABLE_ENTITY.getReasonPhrase(), INSUFFICIENT_AMOUNT);
        }
    }
}
