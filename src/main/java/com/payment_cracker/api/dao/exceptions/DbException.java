package com.payment_cracker.api.dao.exceptions;

/**
 * Created by Alex on 13.01.2015.
 */
public class DbException extends Exception {
    public DbException(){
        super();
    }
    public DbException(Exception e){
        super(e);
    }
}
