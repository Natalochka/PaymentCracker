package com.payment_cracker.api.dao.utils;

import com.payment_cracker.api.dao.exceptions.DbException;

/**
 * Created by Александр on 2/13/2015.
 */
public class CurrencyManualySetter {
    public static void main(String[] args) throws DbException {
        SetCurrencyManualy setCurrencyManualy = new SetCurrencyManualy();
        setCurrencyManualy.setCarrencyManualy();
    }
}
