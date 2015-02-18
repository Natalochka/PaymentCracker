package com.payment_cracker.api.dao.middle_level.action_interfaces;


import com.payment_cracker.api.dao.middle_level.middle_entities.CreditCardEntity;
import com.payment_cracker.api.dao.middle_level.middle_entities.PurseEntity;
import com.payment_cracker.api.dao.middle_level.middle_entities.TransactionEntity;
import com.payment_cracker.api.dao.middle_level.middle_entities.UserEntity;
import com.payment_cracker.api.dao.exceptions.DbException;

import java.util.List;

/**
 * Created by Александр on 1/2/2015.
 */
public interface PurseInterface {
    public void add(UserEntity userEntity, PurseEntity purseEntity) throws DbException;
    public PurseEntity getById(Long id) throws DbException;
    public void update(Long id, PurseEntity purseEntity) throws DbException;
    public List<PurseEntity> getAll() throws DbException;

    void makeTransactionFromPurseToPurse(TransactionEntity<PurseEntity, PurseEntity> transactionEntity) throws DbException;

    void putMoneyOnPurseFromCreditCard(TransactionEntity<CreditCardEntity, PurseEntity> transactionEntity) throws DbException;

    void putMoneyOnCreditCardFromPurse(TransactionEntity<PurseEntity, CreditCardEntity> transactionEntity) throws DbException;
}
