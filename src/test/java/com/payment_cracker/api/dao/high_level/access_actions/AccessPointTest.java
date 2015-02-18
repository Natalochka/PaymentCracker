package com.payment_cracker.api.dao.high_level.access_actions;

import com.payment_cracker.api.dao.exceptions.DbException;
import com.payment_cracker.api.dao.utils.SessionManager;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.sql.SQLException;

import static junit.framework.Assert.assertTrue;

/**
 * Created by Natalie on 12.02.2015.
 */
@RunWith(JUnit4.class)
public class AccessPointTest {
    private AccessPoint accessPoint;

    @Before
    public void init() throws InterruptedException, SQLException, DbException {
        accessPoint = new AccessPoint();
    }

    @Test
    public void testConnect() throws Exception {
        assertTrue(accessPoint.connect("a1", "a1"));
    }

    @Test
    public void testCreateUser() throws Exception {
        accessPoint.createUser("c2", "c2", "ABC", "my@email.com", "380671111111");
    }

    @Test
    public void testSetBan() throws Exception {
        accessPoint.setBan(123456545678L, true);
    }

    @Test
    public void testSetAccountStage() throws Exception {

    }

    @Test
    public void testMakeAdministrator() throws Exception {

    }

    @Test
    public void testGetAccountInfo() throws Exception {

    }

    @Test
    public void testGetAccountInfoById() throws Exception {

    }

    @Test
    public void testCreatePurse() throws Exception {

    }

    @Test
    public void testGetPursesInfo() throws Exception {

    }

    @Test
    public void testClose() throws Exception {

    }

    @Test
    public void testFinalClose() throws Exception {

    }

    @Test
    public void testMakeTransactionFromPurseToPurse() throws Exception {

    }

    @Test
    public void testPutMoneyOnPurseFromCreditCard() throws Exception {

    }

    @Test
    public void testPutMoneyOnCreditCardFromPurse() throws Exception {

    }

    @Test
    public void testUpdateCurrencies() throws Exception {

    }

    @Test
    public void testGetPurseByCurrencyLabel() throws Exception {

    }
}
