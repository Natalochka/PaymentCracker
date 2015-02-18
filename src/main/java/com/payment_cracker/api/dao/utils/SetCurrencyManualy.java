package com.payment_cracker.api.dao.utils;

import com.payment_cracker.api.dao.exceptions.DbException;
import com.payment_cracker.api.dao.middle_level.middle_actions.Services;
import com.payment_cracker.api.dao.middle_level.middle_actions.CurrencyServices;
import com.payment_cracker.api.dao.middle_level.middle_entities.CurrencyEntity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Александр on 2/12/2015.
 */
public class SetCurrencyManualy extends Services {
    private SessionManager sessionManager;
    public CurrencyServicesClass currencyActionsClass2;
    public SetCurrencyManualy() {
        super(new SessionManager());
        this.sessionManager = getSessionManager();
        currencyActionsClass2 = super.currencyActionsClass;
    }
    public void setCarrencyManualy() throws DbException {
        sessionManager.startTransaction();
        CurrencyServices currencyServices = new CurrencyServices(sessionManager);
        List<CurrencyEntity> currencyEntityList = new ArrayList<CurrencyEntity>();
        Calendar currentCalendar = Calendar.getInstance();
        Date currentDate = currentCalendar.getTime();

        CurrencyEntity usdEntity = new CurrencyEntity();
        usdEntity.setCurrencyValue(1.00);
        usdEntity.setCurrencyLabel("USD");
        usdEntity.setCurrencyDate(currentDate);
        currencyEntityList.add(usdEntity);

        CurrencyEntity eurEntity = new CurrencyEntity();
        eurEntity.setCurrencyValue(1.1315);
        eurEntity.setCurrencyLabel("EUR");
        eurEntity.setCurrencyDate(currentDate);
        currencyEntityList.add(eurEntity);

        CurrencyEntity uahEntity = new CurrencyEntity();
        uahEntity.setCurrencyValue(0.0396);
        uahEntity.setCurrencyLabel("UAH");
        uahEntity.setCurrencyDate(currentDate);
        currencyEntityList.add(uahEntity);

        for (CurrencyEntity currencyEntity : currencyEntityList) {
            currencyEntity.setCurrencyId(DAOUtils.generateId());
            currencyActionsClass2.add(currencyEntity);
        }
        sessionManager.commitTransaction();
        sessionManager.closeSession();
    }
}
