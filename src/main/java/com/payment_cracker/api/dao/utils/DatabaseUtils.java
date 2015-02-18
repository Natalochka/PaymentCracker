package com.payment_cracker.api.dao.utils;

import com.payment_cracker.api.dao.basic_level.basic_entities.Attributes;
import com.payment_cracker.api.dao.basic_level.basic_entities.ObjType;
import com.payment_cracker.api.dao.exceptions.DbException;
import com.payment_cracker.api.dao.middle_level.middle_actions.Services;
import com.payment_cracker.api.dao.middle_level.middle_entities.CreditCardEntity;
import org.flywaydb.core.Flyway;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Александр on 2/10/2015.
 */
public class DatabaseUtils extends Services {
    private SessionManager sessionManager;
    public DatabaseUtils() {
        super(new SessionManager());
        this.sessionManager = getSessionManager();
    }
    public void generateDatabase() throws DbException, SQLException {
        if (sessionManager.getSession().createQuery("select 1 from Objects").setMaxResults(1).list().isEmpty()) {
            sessionManager.startTransaction();
            List<ObjType> objTypeList = new ArrayList<ObjType>();
            List<Attributes> attributesList = new ArrayList<Attributes>();

            objTypeList.add(0, (new ObjType(TablesInfo.getUsersObjTypeId(), "Users")));
            objTypeList.add(1, (new ObjType(TablesInfo.getCurrencyObjTypeId(), "Currency")));
            objTypeList.add(2, (new ObjType(TablesInfo.getPurseObjTypeId(), "Purse")));
            objTypeList.add(3, (new ObjType(TablesInfo.getCreditCardObjectTypeId(), "CreditCard")));
            objTypeList.add(4, (new ObjType(TablesInfo.getTransactionInfoObjTypeId(), "Transactions")));
            objTypeList.add(5, (new ObjType(TablesInfo.getObjTypeMessage(), "Messages")));

            int u = 0;
            for (Users column : Users.values()) {
                attributesList.add(u, new Attributes(column.getAttributeId(), objTypeList.get(0), column.getAttributeType().getTypeId(),
                        column.getColumnName()));
                u++;
            }


            for (Currency column : Currency.values()) {
                attributesList.add(u, new Attributes(column.getAttributeId(), objTypeList.get(1), column.getAttributeType().getTypeId(),
                        column.getColumnName()));
                u++;
            }

            for (Purse column : Purse.values()) {
                attributesList.add(u, new Attributes(column.getAttributeId(), objTypeList.get(2), column.getAttributeType().getTypeId(),
                        column.getColumnName()));
                u++;
            }

            for (CreditCard column : CreditCard.values()) {
                attributesList.add(u, new Attributes(column.getAttributeId(), objTypeList.get(3), column.getAttributeType().getTypeId(),
                        column.getColumnName()));
                u++;
            }

            for (TransactionInfo column : TransactionInfo.values()) {
                attributesList.add(u, new Attributes(column.getAttributeId(), objTypeList.get(4), column.getAttributeType().getTypeId(),
                        column.getColumnName()));
                u++;
            }

            for (Messages column : Messages.values()) {
                attributesList.add(u, new Attributes(column.getAttributeId(), objTypeList.get(5), column.getAttributeType().getTypeId(),
                        column.getColumnName()));
                u++;
            }

            for (ObjType objType : objTypeList) {
                sessionManager.getSession().save(objType);
            }

            for (Attributes attributes : attributesList) {
                sessionManager.getSession().save(attributes);
            }

            currencyActionsClass.updateAllCurrencyByYahooAPI();

            sessionManager.commitTransaction();
            sessionManager.startTransaction();

            CreditCardEntity creditCardEntity = new CreditCardEntity();
            creditCardEntity.setId(1l);
            creditCardEntity.setBalance(10000000000.00);
            creditCardEntity.setCurrencyId(currencyActionsClass.getAll().get(0).getCurrencyId());
            creditCardActionsClass.add(creditCardEntity);
            CreditCardEntity creditCardEntity2 = new CreditCardEntity();
            creditCardEntity2.setId(2l);
            creditCardEntity2.setBalance(0.00);
            creditCardEntity2.setCurrencyId(currencyActionsClass.getAll().get(0).getCurrencyId());
            creditCardActionsClass.add(creditCardEntity2);
            sessionManager.commitTransaction();
            Flyway flyway = new Flyway();
            flyway.setDataSource("jdbc:oracle:thin:@localhost:1521/xe", "PaymentCracker", "PaymentCracker");
            flyway.baseline();
            flyway.migrate();
        }
        sessionManager.closeSession();
        sessionManager.closeFactory();
    }
}

