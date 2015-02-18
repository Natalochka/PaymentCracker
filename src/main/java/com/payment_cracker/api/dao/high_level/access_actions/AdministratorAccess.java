package com.payment_cracker.api.dao.high_level.access_actions;


import com.payment_cracker.api.dao.exceptions.DbException;
import com.payment_cracker.api.dao.high_level.access_interfaces.UserAccess;
import com.payment_cracker.api.dao.utils.SessionManager;

/**
 * Created by Александр on 1/8/2015.
 */
public class AdministratorAccess extends ClientAccess implements UserAccess {
    private SessionManager sessionManager;

    public AdministratorAccess(SessionManager sessionManager) {
        super(sessionManager);
        this.sessionManager = sessionManager;
    }

    @Override
    public void setBan(Long id, boolean banStage) throws DbException {
        administratorActionsClass.setBan(id, banStage);
    }

    @Override
    public void setAccountStage(Long id, boolean accountStage) throws DbException {
        administratorActionsClass.setAccountStage(id, accountStage);
    }

    @Override
    public void makeAdministrator(Long id, boolean isAdmin) throws DbException {
        administratorActionsClass.makeAdministrator(id, isAdmin);
    }
}
