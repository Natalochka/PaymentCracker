package com.payment_cracker.api.dao.utils;

/**
 * Created by Александр on 1/17/2015.
 */
public enum  CurrencyTypes {
    UAH("UAH"),
    USD("USD"),
    EUR("EUR");
    public String currencyName;
    CurrencyTypes(String currencyName) {
        this.currencyName = currencyName;
    }

    public String getCurrencyName() {
        return currencyName;
    }
}
