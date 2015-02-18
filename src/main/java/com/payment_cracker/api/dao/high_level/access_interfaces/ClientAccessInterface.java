package com.payment_cracker.api.dao.high_level.access_interfaces;

import com.payment_cracker.api.dao.exceptions.DbException;
import com.payment_cracker.api.dao.middle_level.middle_entities.UserEntity;

/**
 * Created by Александр on 1/5/2015.
 */
public interface ClientAccessInterface {
    public String getAccountInfo(Long id) throws DbException;
    public void createPurse(UserEntity userEntity, String currencyLabel) throws DbException;
    public void makeTransactionFromPurseToPurse(Long idFrom, Long idWhere, Double moneyValue) throws DbException;
    public void putMoneyOnPurseFromCreditCard(Long purseId, Long creditCardId, Double money) throws DbException;
    public void putMoneyOnCreditCardFromPurse(Long creditCardId, Long purseId, Double money) throws DbException;
}
