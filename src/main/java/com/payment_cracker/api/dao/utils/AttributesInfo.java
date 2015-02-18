package com.payment_cracker.api.dao.utils;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Класс констант. Все изменения и вносимые дополнения должны проверятся
 * вносящим изменения.
 *
 *  Методы, описанные в классе, возвращают список параметров, добавлять мануально.
 *
 * @param "QUANTITY_ATTRIBUTES_OF_" "ENTITY" - количество атрибутов типа "ENTITY".
 *                                          Также проверять при изменении.
 *
 * @param "QUANTITY_REF_OF_" "ENTITY" - количество связей "ENTITY" с другими
 *                                   сущностями.Также проверять при изменении.
 */
public class AttributesInfo {

    private AttributesInfo() {}
    
    public static final int ATTR_PASSWORD_OF_USER = 1;
    public static final int ATTR_FIO_OF_USER = 2;
    public static final int ATTR_PHONE_NUMBER_OF_USER = 3;
    public static final int ATTR_EMAIL_OT_USER = 4;
    public static final int ATTR_REG_DATE_OF_USER = 5;
    public static final int ATTR_ACTIVE_OF_USER = 6;
    public static final int ATTR_BAN_OF_USER = 7;
    public static final int ATTR_ADMINISTRATOR_OF_USER = 8;

    public static final int QUANTITY_ATTRIBUTES_OF_USER = 8;

    public static final int ATTR_LABEL_OF_CURRENCY = 9;
    public static final int ATTR_VALUE_OF_CURRENCY = 10;
    public static final int ATTR_DATE_OF_CURRENCY = 11;

    public static final int QUANTITY_ATTRIBUTES_OF_CURRENCY = 3;

    public static final int ATTR_BALANCE_OF_PURSE = 12;
    public static final int ATTR_CURRENCY_ID_OF_PURSE = 13;

    public static final int QUANTITY_ATTRIBUTES_OF_PURSE = 1;
    public static final int QUANTITY_REF_OF_PURSE = 1;


    public static final int ATTR_BALANCE_OF_CREDIT_CARD = 14;
    public static final int ATTR_CURRENCY_ID_OF_CREDIT_CARD = 15;

    public static final int QUANTITY_ATTRIBUTES_OF_CREDIT_CARD = 1;
    public static final int QUANTITY_REF_OF_CREDIT_CARD = 1;

    public static final int ATTR_DATE_OF_MESSAGE = 16;
    public static final int ATTR_TEXT_OF_MESSAGE = 17;
    public static final int ATTR_CONFIRM_TOKEN_OF_MESSAGE = 18;


    public static final int QUANTITY_ATTRIBUTES_OF_MESSAGE = 3;


    public static final int ATTR_SENDER_PURSE_OF_TRANSACTION = 19;
    public static final int ATTR_RECEIVER_PURSE_OF_TRANSACTION = 20;
    public static final int ATTR_AMOUNT_OF_TRANSACTION = 21;
    public static final int ATTR_SENDER_CURR_VAL_OF_TRANSACTION = 22;
    public static final int ATTR_RECEIVER_CURR_VAL_OF_TRANSACTION = 23;
    public static final int ATTR_DATE_OF_TRANSACTION = 24;

    public static final int QUANTITY_ATTRIBUTES_OF_TRANSACTION = 4;
    public static final int QUANTITY_REF_OF_TRANSACTION = 2;


    public static final List<Integer> getAttributesOfUser()
    {
        List<Integer> attributeses = new ArrayList<>();
        attributeses.add(ATTR_PASSWORD_OF_USER);
        attributeses.add(ATTR_FIO_OF_USER);
        attributeses.add(ATTR_EMAIL_OT_USER);
        attributeses.add(ATTR_REG_DATE_OF_USER);
        attributeses.add(ATTR_PHONE_NUMBER_OF_USER);
        attributeses.add(ATTR_ACTIVE_OF_USER);
        attributeses.add(ATTR_BAN_OF_USER);
        attributeses.add(ATTR_ADMINISTRATOR_OF_USER);
        return attributeses;
    }

    public static final List<Integer> getAttributesOfPurse(){
        List<Integer> attributeses = new ArrayList<>();
        attributeses.add(ATTR_BALANCE_OF_PURSE);
        return attributeses;
    }


    public static final List<Integer> getRefOfPurse(){
        List<Integer> attributeses = new ArrayList<>();
        attributeses.add(ATTR_CURRENCY_ID_OF_PURSE);
        return attributeses;
    }


    public static final List<Integer> getAttributesOfCreditCard(){
        List<Integer> attributeses = new ArrayList<>();
        attributeses.add(ATTR_BALANCE_OF_CREDIT_CARD);
        return attributeses;
    }


    public static final List<Integer> getRefOfCreditCard(){
        List<Integer> attributeses = new ArrayList<>();
        attributeses.add(ATTR_CURRENCY_ID_OF_CREDIT_CARD);
        return attributeses;
    }

    public static final List<Integer> getAttributesOfMessage(){
        List<Integer> attributeses = new ArrayList<>();
        attributeses.add(ATTR_DATE_OF_MESSAGE);
        attributeses.add(ATTR_TEXT_OF_MESSAGE);
        attributeses.add(ATTR_CONFIRM_TOKEN_OF_MESSAGE);
        return attributeses;
    }

    public static final List<Integer> getAttributesOfCurrency() {
        List<Integer> attributeses = new ArrayList<>();
        attributeses.add(ATTR_DATE_OF_CURRENCY);
        attributeses.add(ATTR_VALUE_OF_CURRENCY);
        return attributeses;
    }

    public static final List<Integer> getAttributesOfTransaction()
    {
        List<Integer> attributeses = new ArrayList<>();

        attributeses.add(ATTR_DATE_OF_TRANSACTION);
        attributeses.add(ATTR_AMOUNT_OF_TRANSACTION);
        attributeses.add(ATTR_SENDER_CURR_VAL_OF_TRANSACTION);
        attributeses.add(ATTR_RECEIVER_CURR_VAL_OF_TRANSACTION);

        return attributeses;
    }

    public static final List<Integer> getRefOfTransaction(){
        List<Integer> attributeses = new ArrayList<>();
        attributeses.add(ATTR_SENDER_PURSE_OF_TRANSACTION);
        attributeses.add(ATTR_RECEIVER_PURSE_OF_TRANSACTION);
        return attributeses;
    }

}
