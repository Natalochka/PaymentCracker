package com.payment_cracker.api.dao.high_level.access_interfaces;

import com.payment_cracker.api.dao.exceptions.DbException;

/**
 * Created by Александр on 1/5/2015.
 */
public interface AdministratorAccessInterface extends ClientAccessInterface {
    public void setBan(Long id, boolean banStage) throws DbException;
    public void setAccountStage(Long id, boolean accountStage) throws DbException;
    public void makeAdministrator(Long id, boolean isAdmin) throws DbException;
}
