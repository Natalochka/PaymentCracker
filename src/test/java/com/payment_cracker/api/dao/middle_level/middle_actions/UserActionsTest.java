package com.payment_cracker.api.dao.middle_level.middle_actions;

import com.payment_cracker.api.dao.exceptions.DbException;
import com.payment_cracker.api.dao.middle_level.middle_entities.UserEntity;
import com.payment_cracker.api.dao.utils.SessionManager;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Created by Александр on 2/9/2015.
 */
@RunWith(JUnit4.class)
public class UserActionsTest {
    private UserServices userActions;
    private SessionManager sessionManager;


    @Before
    public void init() throws InterruptedException, SQLException, DbException {
        sessionManager = new SessionManager();
        sessionManager.startSession();
        userActions = new UserServices(sessionManager);
    }

    @Test
    public void checkUserActions() throws DbException {
        sessionManager.startTransaction();
        UserEntity userEntity = new UserEntity("aaa", "aaa", "aaa", "012123", "aaa", new Date(), true, false, true);
        userActions.add(userEntity);
        List<UserEntity> all = userActions.getAll();
        Long userId = null;
        for(int i = 0; i < all.size(); i++) {
            if(all.get(i).getLogin().equals(userEntity.getLogin() ) &&
                    all.get(i).getPassword().equals(userEntity.getPassword() ) &&
                    all.get(i).getEmail().equals(userEntity.getEmail() ) &&
                    all.get(i).getPhoneNumber().equals(userEntity.getPhoneNumber()) &&
                    all.get(i).getRegDate().equals(userEntity.getRegDate()) &&
                    all.get(i).isActive() == userEntity.isActive() &&
                    all.get(i).isAdministrator() == userEntity.isAdministrator() &&
                    all.get(i).isBan() == userEntity.isBan()) {
                        userId = all.get(i).getId();
            }
        }

        UserEntity userById = userActions.getById(userId);
        assertTrue(userById.getLogin().equals(userEntity.getLogin()));
        assertTrue(userById.getPassword().equals(userEntity.getPassword()));
        assertTrue(userById.getEmail().equals(userEntity.getEmail()));
        assertTrue(userById.getPhoneNumber().equals(userEntity.getPhoneNumber()));
        assertTrue(userById.getRegDate() == userEntity.getRegDate());
        assertTrue(userById.isActive() == userEntity.isActive());
        assertTrue(userById.isAdministrator() == userEntity.isAdministrator());
        assertTrue(userById.isBan() == userEntity.isBan());
        UserEntity userEntity3 = new UserEntity("bbb", "bbb", "bbb", "012123", "bbb", new Date(), true, false, true);
        userActions.update(userId, userEntity3);
        userById = userActions.getById(userId);
        assertTrue(userById.getLogin().equals(userEntity3.getLogin()));
        assertTrue(userById.getPassword().equals(userEntity3.getPassword()));
        assertTrue(userById.getEmail().equals(userEntity3.getEmail()));
        assertTrue(userById.getPhoneNumber().equals(userEntity3.getPhoneNumber()));
        assertTrue(userById.getRegDate() == userEntity3.getRegDate());
        assertTrue(userById.isActive() == userEntity3.isActive());
        assertTrue(userById.isAdministrator() == userEntity3.isAdministrator());
        assertTrue(userById.isBan() == userEntity3.isBan());
        sessionManager.commitTransaction();
    }

    @Test
    public void add() {

    }
}
