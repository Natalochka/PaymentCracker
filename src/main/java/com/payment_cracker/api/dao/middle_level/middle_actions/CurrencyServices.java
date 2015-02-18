package com.payment_cracker.api.dao.middle_level.middle_actions;

import com.payment_cracker.api.dao.basic_level.basic_dao.BasicDao;
import com.payment_cracker.api.dao.basic_level.basic_entities.*;
import com.payment_cracker.api.dao.exceptions.DbException;
import com.payment_cracker.api.dao.middle_level.middle_entities.CurrencyEntity;
import com.payment_cracker.api.dao.utils.DAOUtils;
import com.payment_cracker.api.dao.utils.SessionManager;
import com.payment_cracker.api.dao.utils.TablesInfo;
import org.hibernate.Session;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Александр on 02.02.2015.
 */
public class CurrencyServices extends BasicDao {
    private SessionManager sessionManager;
    private Session session;

    public CurrencyServices(SessionManager sessionManager) {
        super(sessionManager.getSession());
        this.sessionManager = sessionManager;
        session = sessionManager.getSession();
    }

    protected void add(CurrencyEntity currencyEntity) throws DbException {
        Long objectId = currencyEntity.getCurrencyId();
        Objects tempObject = new Objects();
        tempObject.setObjectId(objectId);
        tempObject.setName(currencyEntity.getCurrencyLabel());
        tempObject.setObjectType(new ObjType().setObjectTypeId(TablesInfo.getCurrencyObjTypeId()));
        List<Attributes> attributes = getAttributesList(TablesInfo.getCurrencyObjTypeId());
        super.<Objects>addEntity(tempObject);
        List<Parameters> parameters = currencyEntity.getParameters(tempObject, attributes);
        for (Parameters parameter : parameters) {
            super.<Parameters>addEntity(parameter);
        }
    }

    protected void update(long id, CurrencyEntity currencyEntity) throws DbException {
        Objects tempObject = super.<Objects, Long>getByIdEntity(id, Objects.class);
        List<Parameters> currentParameters = getParametersList(tempObject);
        List<ParametersId> parametersIds = new ArrayList<ParametersId>();
        for(int i = 0; i < TablesInfo.getCurrencyCountOfEntityAttributes(); i++) {
            parametersIds.add(currentParameters.get(i).getParametersId());
        }
        List<Parameters> newParameters = currencyEntity.getParametersByParametersId(parametersIds);
        for (int i = 0; i < TablesInfo.getCurrencyCountOfEntityAttributes(); i++) {
            currentParameters.get(i).setValue(newParameters.get(i).getValue());
            currentParameters.get(i).setDateValue(newParameters.get(i).getDateValue());
        }
        for (Parameters currentParameter : currentParameters) {
            super.<Parameters>addEntity(currentParameter);
        }
    }

    protected CurrencyEntity getById(Long id) throws DbException {
        Objects objects = super.<Objects, Long>getByIdEntity(id, Objects.class);
        List<Parameters> parameters = getParametersList(objects);
        CurrencyEntity currencyEntity = new CurrencyEntity(parameters);
        currencyEntity.setCurrencyId(id);
        return currencyEntity;
    }

    protected void updateAllCurrencyByYahooAPI() throws DbException {
        List<CurrencyEntity> currencyEntities = DAOUtils.getCurrencyRate();
        for (CurrencyEntity currencyEntity : currencyEntities) {
            currencyEntity.setCurrencyId(DAOUtils.generateId());
            add(currencyEntity);
        }
    }

    protected List<CurrencyEntity> getAll() {
        List<CurrencyEntity> allCurrencies = new ArrayList<CurrencyEntity>();
        List<Objects> listOfObjects = getAllObjectsByObjTypeId(TablesInfo.getCurrencyObjTypeId());
        if(listOfObjects != null) {
            for (int i = 0; i < listOfObjects.size(); i++) {
                Objects object = listOfObjects.get(i);
                List<Parameters> tempParametersList = getParametersList(object);
                allCurrencies.add(new CurrencyEntity(tempParametersList));
                allCurrencies.get(i).setCurrencyId(object.getObjectId());
            }
        }
        return allCurrencies;
    }
}
