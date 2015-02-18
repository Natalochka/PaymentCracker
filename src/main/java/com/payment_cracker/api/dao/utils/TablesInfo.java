package com.payment_cracker.api.dao.utils;

/**
 * Created by Александр on 01.02.2015.
 */
public class TablesInfo {
    private static final Integer usersObjTypeId = 1;
    private static final Integer currencyObjTypeId = 2;
    private static final Integer purseObjTypeId = 3;
    private static final Integer CREDIT_CARD_OBJECT_TYPE_ID = 4;
    private static final Integer TRANSACTION_INFO_OBJ_TYPE_ID = 5;
    public  static final Integer OBJ_TYPE_MESSAGE = 6;
    private static final Integer objTypeSize = 6;

    private static final Integer usersCountOfEntityAttributes = 8;
    private static final Integer currencyCountOfEntityAttributes = 3;
    private static final Integer purseCountOfEntityAttributes = 1;
    private static final Integer creditCardCountOfEntityAttributes = 1;
    private static final Integer transactionInfoCountOfEntityAttributes = 4;

    public static Integer getObjTypeMessage() {
        return OBJ_TYPE_MESSAGE;
    }

    private static final Integer messageCountOfEntityAttributes = 5;

    public static Integer getObjTypeSize() {
        return objTypeSize;
    }

    public static Integer getUsersObjTypeId() {
        return usersObjTypeId;
    }

    public static Integer getUsersCountOfEntityAttributes() {
        return usersCountOfEntityAttributes;
    }

    public static Integer getCurrencyObjTypeId() {
        return currencyObjTypeId;
    }

    public static Integer getCurrencyCountOfEntityAttributes() {
        return currencyCountOfEntityAttributes;
    }

    public static Integer getPurseObjTypeId() {
        return purseObjTypeId;
    }

    public static Integer getPurseCountOfEntityAttributes() {
        return purseCountOfEntityAttributes;
    }

    public static Integer getCreditCardObjectTypeId() {
        return CREDIT_CARD_OBJECT_TYPE_ID;
    }

    public static Integer getCreditCardCountOfEntityAttributes() {
        return creditCardCountOfEntityAttributes;
    }

    public static Integer getTransactionInfoObjTypeId() {
        return TRANSACTION_INFO_OBJ_TYPE_ID;
    }

    public static Integer getTransactionInfoCountOfEntityAttributes() {
        return transactionInfoCountOfEntityAttributes;
    }


    public static Integer getMessageCountOfEntityAttributes() {
        return messageCountOfEntityAttributes;
    }
}


