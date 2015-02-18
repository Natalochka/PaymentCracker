package com.payment_cracker.api.dao.middle_level.middle_actions;

import com.payment_cracker.api.dao.exceptions.DbException;
import com.payment_cracker.api.dao.middle_level.middle_entities.*;
import com.payment_cracker.api.dao.utils.SessionManager;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Александр on 2/13/2015.
 */
public class Services {
    private SessionManager sessionManager;
    protected AdministratorServicesClass administratorActionsClass;
    protected CreditCardServicesClass creditCardActionsClass;
    protected CurrencyServicesClass currencyActionsClass;
    protected MessageServicesClass messageActionsClass;
    protected PurseServicesClass purseActionsClass;
    protected UserServicesClass userActionsClass;

    public Services(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
        this.administratorActionsClass = new AdministratorServicesClass(sessionManager);
        this.creditCardActionsClass = new CreditCardServicesClass(sessionManager);
        this.currencyActionsClass = new CurrencyServicesClass(sessionManager);
        this.messageActionsClass = new MessageServicesClass(sessionManager);
        this.purseActionsClass = new PurseServicesClass(sessionManager);
        this.userActionsClass = new UserServicesClass(sessionManager);
    }

    protected void setSessionManager(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    protected class AdministratorServicesClass extends AdministratorServices {
        public AdministratorServicesClass(SessionManager sessionManager) {
            super(sessionManager);
        }

        @Override
        public void setBan(Long id, boolean banStage) throws DbException {
            super.setBan(id, banStage);
        }

        @Override
        public void setAccountStage(Long id, boolean accountStage) throws DbException {
            super.setAccountStage(id, accountStage);
        }

        @Override
        public void makeAdministrator(Long id, boolean isAdmin) throws DbException {
            super.makeAdministrator(id, isAdmin);
        }

        @Override
        public void add(UserEntity entity) throws DbException {
            super.add(entity);
        }

        @Override
        public void update(Long id, UserEntity userEntity) throws DbException {
            super.update(id, userEntity);
        }

        @Override
        public UserEntity getById(Long id) throws DbException {
            return super.getById(id);
        }

        @Override
        public List<UserEntity> getAll() throws DbException {
            return super.getAll();
        }

        @Override
        public Long checkIsUserUnique(String login, String password) {
            return super.checkIsUserUnique(login, password);
        }
    }

    protected class CreditCardServicesClass extends CreditCardServices {
        public CreditCardServicesClass(SessionManager sessionManager) {
            super(sessionManager);
        }

        @Override
        public List<CreditCardEntity> getAll() throws DbException {
            return super.getAll();
        }

        @Override
        public void update(Long id, CreditCardEntity creditCardEntity) throws SQLException, DbException {
            super.update(id, creditCardEntity);
        }

        @Override
        public CreditCardEntity getById(Long id) throws DbException {
            return super.getById(id);
        }

        @Override
        public void add(CreditCardEntity creditCardEntity) throws DbException {
            super.add(creditCardEntity);
        }
    }

    protected class CurrencyServicesClass extends CurrencyServices {
        public CurrencyServicesClass(SessionManager sessionManager) {
            super(sessionManager);
        }

        @Override
        public void add(CurrencyEntity currencyEntity) throws DbException {
            super.add(currencyEntity);
        }

        @Override
        public void update(long id, CurrencyEntity currencyEntity) throws DbException {
            super.update(id, currencyEntity);
        }

        @Override
        public CurrencyEntity getById(Long id) throws DbException {
            return super.getById(id);
        }

        @Override
        public void updateAllCurrencyByYahooAPI() throws DbException {
            super.updateAllCurrencyByYahooAPI();
        }

        @Override
        public List<CurrencyEntity> getAll() {
            return super.getAll();
        }
    }

    protected class MessageServicesClass extends MessageServices {

        public MessageServicesClass(SessionManager sessionManager) {
            super(sessionManager);
        }

        public void add(MessageEntity entity) throws DbException{
            super.add(entity);
        }

        public void update(MessageEntity entity) throws DbException{
            super.update(entity);
        }

        public MessageEntity getById(Long id) throws DbException{
            return super.getById(id);
        }

        public List<MessageEntity> getAll() throws DbException{
            return super.getAll();
        }

        public void sendMessage (MessageEntity entity) throws DbException {
            super.sendMessage(entity);
        }
    }

    protected class PurseServicesClass extends PurseServices {
        public PurseServicesClass(SessionManager sessionManager) {
            super(sessionManager);
        }

        @Override
        public void add(UserEntity userEntity, PurseEntity purseEntity) throws DbException {
            super.add(userEntity, purseEntity);
        }

        @Override
        public void update(Long id, PurseEntity purseEntity) throws DbException {
            super.update(id, purseEntity);
        }

        @Override
        public PurseEntity getById(Long id) throws DbException {
            return super.getById(id);
        }

        @Override
        public List<PurseEntity> getAll() throws DbException {
            return super.getAll();
        }

        @Override
        public void saveTransactionResult(TransactionEntity transactionEntity) throws DbException {
            super.saveTransactionResult(transactionEntity);
        }

        @Override
        public Long getAndCheckPurseByCurrencyType(String currencyType, Long userId) {
            return super.getAndCheckPurseByCurrencyType(currencyType, userId);
        }

        @Override
        public TransactionEntity getTransactionsById(Long id) throws DbException {
            return super.getTransactionsById(id);
        }

        @Override
        public List<TransactionEntity> getAllTransactions() throws DbException {
            return super.getAllTransactions();
        }
    }

    protected class UserServicesClass extends UserServices {
        public UserServicesClass(SessionManager sessionManager) {
            super(sessionManager);
        }

        @Override
        public SessionManager getSessionManager() {
            return super.getSessionManager();
        }

        @Override
        public void add(UserEntity entity) throws DbException {
            super.add(entity);
        }

        @Override
        public void update(Long id, UserEntity userEntity) throws DbException {
            super.update(id, userEntity);
        }

        @Override
        public UserEntity getById(Long id) throws DbException {
            return super.getById(id);
        }

        @Override
        public List<UserEntity> getAll() throws DbException {
            return super.getAll();
        }

        @Override
        public Long checkIsUserUnique(String login, String password) {
            return super.checkIsUserUnique(login, password);
        }
    }

    public SessionManager getSessionManager() {
        return sessionManager;
    }
}
