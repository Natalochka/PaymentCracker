package com.payment_cracker.api.dao.middle_level.middle_actions;

import com.payment_cracker.api.dao.exceptions.DbException;
import com.payment_cracker.api.dao.middle_level.middle_entities.CurrencyEntity;
import com.payment_cracker.api.dao.utils.SessionManager;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.sql.SQLException;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Natalie on 09.02.2015.
 */

@RunWith(JUnit4.class)
public class CurrencyActionsTest {
private CurrencyServices currencyActions;
private SessionManager sessionManager;
private  Date date = new Date();

    @Before
    public void init() throws InterruptedException, SQLException, DbException {
        Locale.setDefault(Locale.ENGLISH);
        sessionManager = new SessionManager();
        sessionManager.startSession();
        currencyActions = new CurrencyServices(sessionManager);
    }

    @Test
    public void testAdd() throws Exception {
        sessionManager.startTransaction();

        CurrencyEntity currencyEntity = new CurrencyEntity();
        currencyEntity.setCurrencyDate(date);
        currencyEntity.setCurrencyId(10L);
        currencyEntity.setCurrencyLabel("UAH");
        currencyEntity.setCurrencyValue(0.04);
        currencyActions.add(currencyEntity);
        sessionManager.commitTransaction();
    }

    @Test
    public void testUpdate() throws Exception {

        sessionManager.startTransaction();
        CurrencyEntity currencyEntity2 = new CurrencyEntity();
        currencyEntity2.setCurrencyDate(date);
        currencyEntity2.setCurrencyId(10L);
        currencyEntity2.setCurrencyLabel("EUR");
        currencyEntity2.setCurrencyValue(1.01);
        currencyActions.update(10L, currencyEntity2);
        sessionManager.commitTransaction();
    }

    @Test
    public void testGetById() throws Exception {

    }

    @Test
    public void testUpdateAllCurrencyByYahooAPI() throws Exception {

    }

    @Test
    public void testGetAll() throws Exception {

    }
}
