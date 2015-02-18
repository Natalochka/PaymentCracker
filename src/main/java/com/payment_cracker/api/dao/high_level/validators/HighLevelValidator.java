package com.payment_cracker.api.dao.high_level.validators;

import com.payment_cracker.api.dao.middle_level.middle_actions.UserServices;
import com.payment_cracker.api.dao.utils.SessionManager;

/**
 * Created by Александр on 2/7/2015.
 */
public class HighLevelValidator {
    private SessionManager sessionManager;
    private UserServices userServices;

    public HighLevelValidator(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
        this.userServices = new UserServices(sessionManager);
    }

//    public Boolean checkIsUserAdministrator(ClientAccess clientAccess, AdministratorAccess administratorAccess) {
//        if (administratorAccess != null) {
//            return true;
//        } else if(clientAccess != null) {
//            return false;
//        }
//        return false;
//    }





//    public boolean
}
