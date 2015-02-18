package com.payment_cracker.api.dao.utils;

import com.payment_cracker.api.dao.exceptions.DbException;

import java.sql.SQLException;
import java.util.Locale;

/**
 * Created by Александр on 2/10/2015.
 */
public class DatabaseGenerator {
    public static void main(String[] args) throws DbException, SQLException {
        Locale.setDefault(Locale.ENGLISH);
        DatabaseUtils databaseUtils = new DatabaseUtils();
        databaseUtils.generateDatabase();
    }
}
