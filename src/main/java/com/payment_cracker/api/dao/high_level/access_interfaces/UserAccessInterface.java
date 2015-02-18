package com.payment_cracker.api.dao.high_level.access_interfaces;

import com.payment_cracker.api.dao.exceptions.DbException;

/**
 * Created by Александр on 1/11/2015.
 */
public interface UserAccessInterface {

    public boolean connect(String login, String password) throws DbException;
    public String createUser(String login, String password, String fio, String phoneNumber, String email) throws DbException;
    public void setBan(Long id, Boolean isBan) throws DbException;
}
