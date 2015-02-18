package com.payment_cracker.api.dao.middle_level.middle_actions;

import com.payment_cracker.api.dao.basic_level.basic_dao.BasicDao;
import com.payment_cracker.api.dao.basic_level.basic_entities.*;
import com.payment_cracker.api.dao.exceptions.DbException;
import com.payment_cracker.api.dao.middle_level.middle_entities.PaymentEntity;
import com.payment_cracker.api.dao.middle_level.middle_entities.PurseEntity;
import com.payment_cracker.api.dao.middle_level.middle_entities.TransactionEntity;
import com.payment_cracker.api.dao.middle_level.middle_entities.UserEntity;
import com.payment_cracker.api.dao.utils.*;
import org.hibernate.Session;
import org.hibernate.jdbc.ReturningWork;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Александр on 02.02.2015.
 */
public class PurseServices extends BasicDao {
    private SessionManager sessionManager;
    private Session session;

    public PurseServices(SessionManager sessionManager) {
        super(sessionManager.getSession());
        this.sessionManager = sessionManager;
        session = sessionManager.getSession();
    }

    protected void add(UserEntity userEntity, PurseEntity purseEntity) throws DbException {
        Long objectId = purseEntity.getId();
        Objects user = super.<Objects, Long>getByIdEntity(userEntity.getId(), Objects.class);
        Objects tempObject = new Objects();
        tempObject.setObjectId(objectId);
        tempObject.setName(userEntity.getLogin() + " " + (new CurrencyServices(sessionManager)).getById(purseEntity.getCurrencyId()).getCurrencyLabel());
        tempObject.setObjectType(new ObjType().setObjectTypeId(TablesInfo.getPurseObjTypeId()));
        tempObject.setParent(user);
        List<Attributes> attributes = getAttributesList(TablesInfo.getPurseObjTypeId());
        super.<Objects>addEntity(tempObject);
        ObjReference objReference = new ObjReference();
        objReference.setObjReferenceId(
                new ObjReferenceId(
                        super.<Attributes, Integer>getByIdEntity(Purse.CURRENCY_ID.getAttributeId(), Attributes.class),
                        super.<Objects, Long>getByIdEntity(purseEntity.getCurrencyId(), Objects.class),
                        tempObject));
        addEntity(objReference);
        List<Parameters> parameters = purseEntity.getParameters(tempObject, attributes);
        for (Parameters parameter : parameters) {
            addEntity(parameter);
        }
    }

    protected void update(Long id, PurseEntity purseEntity) throws DbException {
        Objects tempObject = super.<Objects, Long>getByIdEntity(id, Objects.class);
        List<Parameters> currentParameters = getParametersList(tempObject);
        List<ParametersId> parametersIds = new ArrayList<ParametersId>();
        for(int i = 0; i < TablesInfo.getPurseCountOfEntityAttributes(); i++) {
            parametersIds.add(currentParameters.get(i).getParametersId());
        }
        List<Parameters> newParameters = purseEntity.getParametersByParametersId(parametersIds);
        for (int i = 0; i < TablesInfo.getPurseCountOfEntityAttributes(); i++) {
            currentParameters.get(i).setValue(newParameters.get(i).getValue());
            currentParameters.get(i).setDateValue(newParameters.get(i).getDateValue());
        }
        for (Parameters currentParameter : currentParameters) {
            updateEntity(currentParameter);
        }
    }
    
    protected PurseEntity getById(Long id) throws DbException {
        Objects tempObject = super.<Objects, Long>getByIdEntity(id, Objects.class);
        List<Parameters> parameters = getParametersList(tempObject);
        PurseEntity purseEntity = new PurseEntity(parameters, tempObject);
        purseEntity.setUserId(tempObject.getParent().getObjectId());
        List<ObjReference> objReferencesOfReference = getObjReferencesOfReference(tempObject);
        purseEntity.setCurrencyId(objReferencesOfReference.get(0).getObjReferenceId().getObject().getObjectId());
        purseEntity.setId(id);
        return purseEntity;
    }

    protected List<PurseEntity> getAll() throws DbException {
        List<PurseEntity> allPurses = new ArrayList<PurseEntity>();
        List<Objects> listOfObjects = getAllObjectsByObjTypeId(TablesInfo.getPurseObjTypeId());
        if(listOfObjects != null) {
            for (int i = 0; i < listOfObjects.size(); i++) {
                Objects tempObject = listOfObjects.get(i);
                List<Parameters> tempParametersList = getParametersList(tempObject);
                PurseEntity purseEntity = new PurseEntity(tempParametersList, tempObject);
                purseEntity.setUserId(tempObject.getParent().getObjectId());
                List<ObjReference> objReferencesOfReference = getObjReferencesOfReference(tempObject);
                purseEntity.setCurrencyId(objReferencesOfReference.get(0).getObjReferenceId().getObject().getObjectId());
                purseEntity.setId(tempObject.getObjectId());
                allPurses.add(purseEntity);
            }
        }
        return allPurses;
    }

    protected TransactionEntity getTransactionsById(Long id) throws DbException {
        Objects tempObject = super.<Objects, Long>getByIdEntity(id, Objects.class);
        List<Parameters> parameters = getParametersList(tempObject);
        TransactionEntity transactionEntity = new TransactionEntity(parameters);
        List<ObjReference> objReferencesOfReference = getObjReferencesOfReference(tempObject);
        transactionEntity.setSender((PaymentEntity) objReferencesOfReference.get(0).getObjReferenceId().getObject());
        transactionEntity.setReceiver((PaymentEntity) objReferencesOfReference.get(1).getObjReferenceId().getObject());
        transactionEntity.setId(id);
        return transactionEntity;
    }

    protected List<TransactionEntity> getAllTransactions() throws DbException {
        List<TransactionEntity> allTransactions = new ArrayList<TransactionEntity>();
        List<Objects> listOfObjects = getAllObjectsByObjTypeId(TablesInfo.getPurseObjTypeId());
        if(listOfObjects != null) {
            for (int i = 0; i < listOfObjects.size(); i++) {
                Objects tempObject = listOfObjects.get(i);
                List<Parameters> tempParametersList = getParametersList(tempObject);
                TransactionEntity transactionEntity = new TransactionEntity(tempParametersList);
                List<ObjReference> objReferencesOfReference = getObjReferencesOfReference(tempObject);
                transactionEntity.setSender((PaymentEntity) objReferencesOfReference.get(0).getObjReferenceId().getObject());
                transactionEntity.setReceiver((PaymentEntity) objReferencesOfReference.get(1).getObjReferenceId().getObject());
                transactionEntity.setId(tempObject.getObjectId());
                allTransactions.add(transactionEntity);
            }
        }
        return allTransactions;
    }

    protected void saveTransactionResult(TransactionEntity transactionEntity) throws DbException {
        sessionManager.startTransaction();
        Long objectId = DAOUtils.generateId();
        Objects newTransactionObject = new Objects();
        newTransactionObject.setObjectId(objectId);
        newTransactionObject.setObjectType(new ObjType().setObjectTypeId(TablesInfo.getTransactionInfoObjTypeId()));
        newTransactionObject.setParent(null);
        newTransactionObject.setName("Transaction " + new Date());
        super.<Objects>addEntity(newTransactionObject);
        List<Attributes> attributes = getAttributesList(TablesInfo.getTransactionInfoObjTypeId());

        ObjReference fromPurse = new ObjReference();
        fromPurse.setObjReferenceId(
                new ObjReferenceId(
                        super.<Attributes, Integer>getByIdEntity(TransactionInfo.PURSE_FROM.getAttributeId(), Attributes.class),
                        super.<Objects, Long>getByIdEntity(transactionEntity.getSender().getId(), Objects.class),
                        newTransactionObject));

        ObjReference wherePurse = new ObjReference();
        wherePurse.setObjReferenceId(
                new ObjReferenceId(
                        super.<Attributes, Integer>getByIdEntity(TransactionInfo.PURSE_WHERE.getAttributeId(), Attributes.class),
                        super.<Objects, Long>getByIdEntity(transactionEntity.getReceiver().getId(), Objects.class),
                        newTransactionObject));

        List<Parameters> parametersList = transactionEntity.getParameters(newTransactionObject, attributes);
        addEntity(fromPurse);
        addEntity(wherePurse);
        for (Parameters parameters : parametersList) {
            addEntity(parameters);
        }
        sessionManager.commitTransaction();
    }

    protected Long getAndCheckPurseByCurrencyType(String currencyType, Long userId) {
        return sessionManager.getSession().doReturningWork(new ReturningWork<Long>() {
            public String currencyType;
            public Long userId;
            public Long result;

            public ReturningWork<Long> setParameters(String currencyType, Long userId) {
                this.currencyType = currencyType;
                this.userId = userId;
                return this;
            }

            @Override
            public Long execute(Connection connection) throws SQLException {
                CallableStatement call = connection.prepareCall("{ ? = call checkIsPurseNew(?,?) }");
                call.registerOutParameter(1, Types.LONGNVARCHAR); // or whatever it is
                call.setString(2, currencyType);
                call.setLong(3, userId);
                call.execute();
                result = call.getLong(1);
                call.close();
                return result;
            }
        }.setParameters(currencyType, userId));
    }
}
