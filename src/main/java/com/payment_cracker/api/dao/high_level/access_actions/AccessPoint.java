package com.payment_cracker.api.dao.high_level.access_actions;


import com.payment_cracker.api.dao.exceptions.DbException;
import com.payment_cracker.api.dao.high_level.access_interfaces.UserAccess;
import com.payment_cracker.api.dao.high_level.access_interfaces.UserAccessInterface;
import com.payment_cracker.api.dao.middle_level.middle_actions.CurrencyServices;
import com.payment_cracker.api.dao.middle_level.middle_actions.Services;
import com.payment_cracker.api.dao.middle_level.middle_entities.CurrencyEntity;
import com.payment_cracker.api.dao.middle_level.middle_entities.MessageEntity;
import com.payment_cracker.api.dao.middle_level.middle_entities.PurseEntity;
import com.payment_cracker.api.dao.middle_level.middle_entities.UserEntity;
import com.payment_cracker.api.dao.utils.CurrencyTypes;
import com.payment_cracker.api.dao.utils.DAOUtils;
import com.payment_cracker.api.dao.utils.ExecutionTimeController;
import com.payment_cracker.api.dao.utils.SessionManager;
import org.apache.log4j.Logger;


import java.sql.SQLException;
import java.util.*;

/**
 * Created by Александр on 1/11/2015.
 */
public class AccessPoint extends Services implements UserAccessInterface {
    private UserEntity userEntity;
    private List<PurseEntity> purseEntities = new ArrayList<PurseEntity>();
    private UserAccess userAccess;
    private SessionManager sessionManager;
    private static final Logger logger = Logger.getLogger("payment");
    public AccessPoint() throws SQLException, InterruptedException, DbException {
        super(new SessionManager());
        Locale.setDefault(Locale.ENGLISH);
        this.sessionManager = getSessionManager();
//        new Thread(new CurrencyUpdater(sessionManager)).start();
    }

    private void setNewSessionManager() {
        sessionManager = new SessionManager();
        setSessionManager(sessionManager);
    }

    @Override
    public boolean connect(String login, String password) throws DbException {
        if(sessionManager == null) {
            setNewSessionManager();
        }
        boolean flag = false;
            sessionManager.startTransaction();
            ExecutionTimeController.startCount();
            userAccess = null;
            Long userId = userActionsClass.checkIsUserUnique(login, password);
            if (userId == -1) {
                logger.debug("User does not exist!");
                return flag;
            } else if (userId == -2) {
                logger.debug("Password is wrong!");
                return flag;
            } else {
                this.userEntity = userActionsClass.getById(userId);
                flag = true;
                if (userEntity.isAdministrator()) {
                    userAccess = (UserAccess) new AdministratorAccess(sessionManager);
                    updatePurses();
                    sessionManager.commitTransaction();
                } else if (!userEntity.isAdministrator()) {
                    userAccess = (UserAccess) new ClientAccess(sessionManager);
                    sessionManager.commitTransaction();
                    updatePurses();
                }
                ExecutionTimeController.endCount();
                logger.debug("Connect " + ExecutionTimeController.getInfo());
            }
        return flag;
    }

    private void updatePurses() throws DbException {
        if (userEntity != null) {
            purseEntities.clear();
            for (CurrencyTypes currencyTypes : CurrencyTypes.values()) {
                Long purseId = purseActionsClass.getAndCheckPurseByCurrencyType(currencyTypes.getCurrencyName(), userEntity.getId());
                if(purseId == -1) {

                }
                else {
                    this.purseEntities.add(purseActionsClass.getById(purseId));
                }

            }
        }
    }

    @Override
    public String createUser(String login, String password, String fio, String phoneNumber, String email) throws DbException { // заблокировать возможность создания одинаковых users
        sessionManager.startTransaction();
        ExecutionTimeController.startCount();
        Long userId = userActionsClass.checkIsUserUnique(login, password);
        if(userId == -1) {
            UserEntity tempUserEntity = new UserEntity(login, password, fio, phoneNumber, email,
                    Calendar.getInstance().getTime(), true, false, false);
            tempUserEntity.setId(DAOUtils.generateId());
            userActionsClass.add(tempUserEntity);

            messageActionsClass.sendMessage(createMessage("Welcome to PaymentCracker. " +
                    "We are glad to see you! You confirm password is 64641654165151 ", tempUserEntity));

            sessionManager.commitTransaction();
            ExecutionTimeController.endCount();

            logger.debug("User creation " + ExecutionTimeController.getInfo());
            return "User is created " + userId;

        } else if(userId == -2) {
            logger.debug("Such login already exists!");
            sessionManager.rollbackTransaction();
            return "Such login already exists";
        } else {
            sessionManager.rollbackTransaction();
            logger.debug("User is not created. Such login and password already exist!");
            return "User is not created. Such login and password already exist!";
        }

    }

    @Override
    public void setBan(Long id, Boolean isBan) throws DbException {
        sessionManager.startTransaction();
        userAccess.setBan(id, isBan);
        sessionManager.commitTransaction();
    }

    public void setAccountStage(Long id, Boolean accountStage) throws DbException {
        sessionManager.startTransaction();
        userAccess.setAccountStage(id, accountStage);
        sessionManager.commitTransaction();
    }

    public void makeAdministrator(Long id, boolean isAdmin) throws DbException {
        userAccess.setAccountStage(id, isAdmin);
    }

    public String getAccountInfo() throws DbException {
        userAccess.getAccountInfo(userEntity.getId());
        return userEntity.toString();
    }

    public String getAccountInfoById(Long id) throws DbException {
        userAccess.getAccountInfo(id);
        UserEntity userEntity1 = userActionsClass.getById(id);
        return userEntity1.toString();
    }

    public String createPurse(CurrencyTypes currencyLabel) throws DbException {
        StringBuilder info = new StringBuilder("Purse is not created");
        if(userEntity.isActive() && !userEntity.isBan()) {
            Long purseId = purseActionsClass.getAndCheckPurseByCurrencyType(currencyLabel.getCurrencyName(), userEntity.getId());
            if (purseId == -1) {
                userAccess.createPurse(userEntity, currencyLabel.getCurrencyName());
                info.setLength(0);
                info.append("Purse is created!");
                logger.debug(info);
                updatePurses();
            } else {
                info.setLength(0);
                info.append("Purse with this currency already exists!");
                logger.debug(info);
                System.err.println(info);
            }
        }
        return info.toString();
    }

    public HashMap<CurrencyEntity, PurseEntity> getPursesInfo() throws DbException {
        HashMap<CurrencyEntity, PurseEntity> tempMap = new HashMap<>();
        for (PurseEntity purseEntity : purseEntities) {
            tempMap.put(currencyActionsClass.getById(purseEntity.getCurrencyId()), purseEntity);
        }
        return tempMap;
    }

    public void close() {
        //sessionManager.closeSession();
        //sessionManager.closeFactory();
        userEntity = null;
        purseEntities = new ArrayList<PurseEntity>();
        userAccess = null;
    }

    public void finalClose() {
        userEntity = null;
        purseEntities = new ArrayList<PurseEntity>();
        userAccess = null;
        sessionManager.closeSession();
        sessionManager.closeFactory();
    }

    public String makeTransactionFromPurseToPurse(Long idSender, Long idReceiver, Double money) throws DbException {
        ExecutionTimeController.startCount();
        StringBuilder info = new StringBuilder("Money transfer operation is not completed!");
        if(userEntity.isActive() && !userEntity.isBan()) {
            for (PurseEntity purseEntity : purseEntities) {
                if (purseEntity.getId().equals(idSender)) {
                    userAccess.makeTransactionFromPurseToPurse(idSender, idReceiver, money);
                    ExecutionTimeController.endCount();
                    info.setLength(0);
                    info.append("Money transfer operation is completed!" + ExecutionTimeController.getInfo());
                    logger.debug(info);
                }
            }
            updatePurses();
        }
        return info.toString();
    }

    public String putMoneyOnPurseFromCreditCard(Long creditCardId, Long purseid, Double money) throws DbException {
        ExecutionTimeController.startCount();
        StringBuilder info = new StringBuilder("Add money operation is not completed!");
        if(userEntity.isActive() && !userEntity.isBan()) {
            userAccess.putMoneyOnPurseFromCreditCard(creditCardId, purseid, money);
            updatePurses();
            ExecutionTimeController.endCount();
            info.setLength(0);
            info.append("Add money operation is completed!" + ExecutionTimeController.getInfo());
            logger.debug(info);
        }
        return info.toString();
    }

    public String putMoneyOnCreditCardFromPurse(Long purseId,Long creditCardId, Double money) throws DbException {
        ExecutionTimeController.startCount();
        StringBuilder info = new StringBuilder("Withdraw money operation is not completed!");
        if(userEntity.isActive() && !userEntity.isBan()) {
            userAccess.putMoneyOnCreditCardFromPurse(purseId, creditCardId, money);
            updatePurses();
            ExecutionTimeController.endCount();
            info.setLength(0);
            info.append("Withdraw money operation is completed!" + ExecutionTimeController.getInfo());
            logger.debug(info);
        }
        return info.toString();
    }

    public void updateCurrencies() throws DbException {
        sessionManager.startTransaction();
        CurrencyServices currencyServices = new CurrencyServices(sessionManager);
        currencyActionsClass.updateAllCurrencyByYahooAPI();
        sessionManager.commitTransaction();
    }

    public PurseEntity getPurseByCurrencyLabel(CurrencyTypes currencyLabel) throws DbException {
        updatePurses();
        if (purseEntities.size() > 0) {
            for (PurseEntity purseEntity : purseEntities) {
                CurrencyEntity currencyById = currencyActionsClass.getById(purseEntity.getCurrencyId());
                if (currencyById.getCurrencyLabel().equals(currencyLabel.getCurrencyName())) {
                    return purseEntity;
                }
            }
            return null;
        } else {
            return null;
        }

    }

    public MessageEntity createMessage(String text, UserEntity user) {
        MessageEntity messageEntity = new MessageEntity()
                .setMessageId(DAOUtils.generateId())
                .setUserId(user.getId())
                .setDate(Calendar.getInstance().getTime())
                .setText(text);
        return messageEntity;
    }
}
