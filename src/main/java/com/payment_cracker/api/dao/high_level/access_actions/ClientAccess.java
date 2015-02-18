package com.payment_cracker.api.dao.high_level.access_actions;


import com.payment_cracker.api.dao.exceptions.DbException;
import com.payment_cracker.api.dao.high_level.access_interfaces.UserAccess;
import com.payment_cracker.api.dao.high_level.validators.HighLevelValidator;
import com.payment_cracker.api.dao.middle_level.middle_actions.*;
import com.payment_cracker.api.dao.middle_level.middle_entities.*;
import com.payment_cracker.api.dao.utils.DAOUtils;
import com.payment_cracker.api.dao.utils.SessionManager;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * Created by Александр on 1/8/2015.
 */
public class ClientAccess extends Services implements UserAccess {
    private SessionManager sessionManager;
    private HighLevelValidator highLevelValidator;
    private static final Double COMMISSION = 0.1;

    public ClientAccess(SessionManager sessionManager) {
        super(sessionManager);
        this.sessionManager = sessionManager;
        this.highLevelValidator = new HighLevelValidator(sessionManager);
    }

    public SessionManager getSessionManager() {
        return sessionManager;
    }

    @Override
    public String getAccountInfo(Long id) throws DbException {
        return userActionsClass.getById(id).toString();
    }

    @Override
    public void createPurse(UserEntity userEntity, String currencyLabel) throws DbException {
        boolean flag = false;
        List<CurrencyEntity> allCurrency = currencyActionsClass.getAll();
        Long currencyId = 0l;
        Date currencyDate = new Date();
        for (CurrencyEntity currencyEntity : allCurrency) {
            if (currencyEntity.getCurrencyLabel().equals(currencyLabel) && currencyDate.after(currencyEntity.getCurrencyDate())) {
                currencyDate = currencyEntity.getCurrencyDate();
                currencyId = currencyEntity.getCurrencyId();
                flag = true;
            }
        }
        if(flag) {
            sessionManager.startTransaction();
            PurseEntity purseEntity = new PurseEntity(currencyId, userEntity.getId(), 0);
            purseEntity.setId(DAOUtils.generateId());
            purseActionsClass.add(userEntity, purseEntity);
            sessionManager.commitTransaction();
            System.err.println("Purse was created for user: " + purseEntity.getUserId());
        } else {
            System.err.println(("Currency type doesn't exists"));
        }
    }

    @Override
    public void makeTransactionFromPurseToPurse(Long idSender, Long idReceiver, Double moneyValue) throws DbException {
        PurseServices purseServices = new PurseServices(sessionManager);
        PurseEntity purse1 = purseActionsClass.getById(idSender);
        PurseEntity purse2 = purseActionsClass.getById(idReceiver);
        TransactionEntity<PurseEntity, PurseEntity> transactionEntity =
                new TransactionEntity<PurseEntity, PurseEntity>(purse1, purse2, moneyValue);
        sessionManager.startTransaction();
        PurseEntity sender = transactionEntity.getSender();
        PurseEntity receiver = transactionEntity.getReceiver();
        Double money = transactionEntity.getMoney();
        Double convertedMoney = convertCurrency(sender.getCurrencyId(), receiver.getCurrencyId(), money);
        transactionEntity.setSenderCurrencyValue(currencyActionsClass.getById(sender.getCurrencyId()).getCurrencyValue());
        transactionEntity.setReceiverCurrencyValue(currencyActionsClass.getById(receiver.getCurrencyId()).getCurrencyValue());
        transactionEntity.setDate(new Date());
        try {
            if ((sender.getBalance() >= money + (money * COMMISSION) )) {
                sender.setBalance(new BigDecimal(sender.getBalance() - (money + (money * COMMISSION)))
                        .setScale(2, BigDecimal.ROUND_CEILING).doubleValue());
                purseActionsClass.update(sender.getId(), sender);
                receiver.setBalance(new BigDecimal(receiver.getBalance() + convertedMoney)
                        .setScale(2, BigDecimal.ROUND_CEILING).doubleValue());
                purseActionsClass.update(receiver.getId(), receiver);
                putCommissionOnDevelopersCount(sender);
                sessionManager.commitTransaction();
                purseActionsClass.saveTransactionResult(transactionEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            sessionManager.rollbackTransaction();
        }
    }

    @Override
    public void putMoneyOnPurseFromCreditCard(Long idSender, Long idReceiver, Double moneyValue) throws DbException {
        CreditCardServices creditCardServices = new CreditCardServicesClass(sessionManager);
        PurseServices purseServices = new PurseServices(sessionManager);
        CreditCardEntity creditCard = creditCardActionsClass.getById(idSender);
        PurseEntity purse = purseActionsClass.getById(idReceiver);
        TransactionEntity<CreditCardEntity, PurseEntity> transactionEntity =
                new TransactionEntity<CreditCardEntity, PurseEntity>(creditCard, purse, moneyValue);
        sessionManager.commitTransaction();
        sessionManager.startTransaction();
        CreditCardEntity sender = transactionEntity.getSender();
        PurseEntity receiver = transactionEntity.getReceiver();
        Double money = transactionEntity.getMoney();
        Double convertedMoney = convertCurrency(sender.getCurrencyId(), receiver.getCurrencyId(), money);
        transactionEntity.setSenderCurrencyValue(currencyActionsClass.getById(sender.getCurrencyId()).getCurrencyValue());
        transactionEntity.setReceiverCurrencyValue(currencyActionsClass.getById(receiver.getCurrencyId()).getCurrencyValue());
        transactionEntity.setDate(new Date());
        try {
            if ((sender.getBalance() >= (money + (money * COMMISSION)))) {
                sender.setBalance(new BigDecimal(sender.getBalance() - (money + (money * COMMISSION)))
                        .setScale(2, BigDecimal.ROUND_CEILING).doubleValue());
                creditCardActionsClass.update(sender.getId(), sender);
                receiver.setBalance(new BigDecimal(receiver.getBalance() + convertedMoney)
                        .setScale(2, BigDecimal.ROUND_CEILING).doubleValue());
                purseActionsClass.update(receiver.getId(), receiver);
                putCommissionOnDevelopersCount(sender);
                sessionManager.commitTransaction();
                purseActionsClass.saveTransactionResult(transactionEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            sessionManager.rollbackTransaction();
        }
    }

    @Override
    public void putMoneyOnCreditCardFromPurse(Long idSender, Long idReceiver, Double moneyValue) throws DbException {
        PurseServices purseServices = new PurseServices(sessionManager);
        CreditCardServices creditCardServices = new CreditCardServicesClass(sessionManager);
        PurseEntity purse = purseActionsClass.getById(idSender);
        CreditCardEntity creditCard = creditCardActionsClass.getById(idReceiver);
        TransactionEntity<PurseEntity, CreditCardEntity> transactionEntity =
                new TransactionEntity<PurseEntity, CreditCardEntity>(purse, creditCard, moneyValue);
        sessionManager.startTransaction();
        PurseEntity sender = transactionEntity.getSender();
        CreditCardEntity receiver = transactionEntity.getReceiver();
        Double money = transactionEntity.getMoney();
        Double convertedMoney = convertCurrency(sender.getCurrencyId(), receiver.getCurrencyId(), money);
        transactionEntity.setSenderCurrencyValue(currencyActionsClass.getById(sender.getCurrencyId()).getCurrencyValue());
        transactionEntity.setReceiverCurrencyValue(currencyActionsClass.getById(receiver.getCurrencyId()).getCurrencyValue());
        transactionEntity.setDate(new Date());
        try {
            if ((sender.getBalance() >= (money + (money * COMMISSION)))) {
                sender.setBalance(new BigDecimal(sender.getBalance() - (money + (money * COMMISSION)))
                        .setScale(2, BigDecimal.ROUND_CEILING).doubleValue());
                purseActionsClass.update(sender.getId(), sender);
                receiver.setBalance(new BigDecimal(receiver.getBalance() + convertedMoney)
                        .setScale(2, BigDecimal.ROUND_CEILING).doubleValue());
                creditCardActionsClass.update(receiver.getId(), receiver);
                putCommissionOnDevelopersCount(sender);
                sessionManager.commitTransaction();
                purseActionsClass.saveTransactionResult(transactionEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            sessionManager.rollbackTransaction();
        }
    }

    private void putCommissionOnDevelopersCount(PaymentEntity paymentEntity) throws DbException, SQLException {
        CreditCardEntity creditCardEntity = creditCardActionsClass.getById(2l);
        creditCardEntity.setBalance(creditCardEntity.getBalance() + (paymentEntity.getBalance() * COMMISSION));
    }

    private Double convertCurrency(Long currencyFromId, Long currencyWhereId, Double money) throws DbException {
        CurrencyEntity currencyFrom = currencyActionsClass.getById(currencyFromId);
        CurrencyEntity currencyWhere = currencyActionsClass.getById(currencyWhereId);
        Double currencyValueFrom = currencyFrom.getCurrencyValue();
        Double currencyValueWhere = currencyWhere.getCurrencyValue();
        Double result = (currencyValueFrom * money) / currencyValueWhere ;
        BigDecimal bigDecimal = new BigDecimal(result);
        bigDecimal = bigDecimal.setScale(2, BigDecimal.ROUND_CEILING);
        result = bigDecimal.doubleValue();
        return result;
    }


    @Override
    public void setBan(Long id, boolean banStage) throws DbException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setAccountStage(Long id, boolean accountStage) throws DbException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void makeAdministrator(Long id, boolean isAdmin) throws DbException {
        throw new UnsupportedOperationException();
    }
}
