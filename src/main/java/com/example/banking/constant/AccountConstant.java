package com.example.banking.constant;

public class AccountConstant {

    public static final String BASIC_URL = "/api/banking";
    public static final String CREATE = "/create";
    public static final String GET_ACCOUNT = "/accounts/{phoneNumber}";
    public static final String DEPOSIT_AMOUNT = "/deposit";
    public static final String WITH_DRAW_AMOUNT = "/withdraw";
    public static final String ERROR_MSG_1 = "Phone Number is not present in DataBase";
    public static final String PHONE_NUMBER_NOT_PRESENT = "Request Body should contain phoneNumber";
    public static final String AMOUNT_NOT_PRESENT = "Request Body should contain Amount";
    public static final String NOT_EXISTS = "Record already exists";
    public static final String INSUFFICIENT_AMOUNT = "Insufficient Amount";
}
