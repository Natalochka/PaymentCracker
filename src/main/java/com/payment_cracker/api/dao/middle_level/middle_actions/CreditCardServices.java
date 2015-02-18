package com.payment_cracker.api.dao.middle_level.middle_actions;

import com.payment_cracker.api.dao.basic_level.basic_dao.BasicDao;
import com.payment_cracker.api.dao.basic_level.basic_entities.*;
import com.payment_cracker.api.dao.exceptions.DbException;
import com.payment_cracker.api.dao.middle_level.middle_entities.CreditCardEntity;
import com.payment_cracker.api.dao.utils.CreditCard;
import com.payment_cracker.api.dao.utils.SessionManager;
import com.payment_cracker.api.dao.utils.TablesInfo;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Александр on 02.02.2015.
 */
public class CreditCardServices extends BasicDao {
    private SessionManager sessionManager;
    private Session session;


    protected CreditCardServices(SessionManager sessionManager) {
        super(sessionManager.getSession());
        this.sessionManager = sessionManager;
        session = sessionManager.getSession();
    }

    protected void add(CreditCardEntity creditCardEntity) throws DbException {
        Long objectId = creditCardEntity.getId();
        Objects tempObject = new Objects();
        tempObject.setObjectId(objectId);
        tempObject.setName("Credit card " + objectId);
        tempObject.setObjectType(new ObjType().setObjectTypeId(TablesInfo.getCreditCardObjectTypeId()));
        List<Attributes> attributes = getAttributesList(TablesInfo.getCreditCardObjectTypeId());
        super.<Objects>addEntity(tempObject);
        ObjReference objReference = new ObjReference();
        objReference.setObjReferenceId(
                new ObjReferenceId(
                        super.<Attributes, Integer>getByIdEntity(CreditCard.CURRENCY_ID.getAttributeId(), Attributes.class),
                        super.<Objects, Long>getByIdEntity(creditCardEntity.getCurrencyId(), Objects.class),
                        tempObject));
        super.<ObjReference>addEntity(objReference);
        List<Parameters> parameters = creditCardEntity.getParameters(tempObject, attributes);
        for (Parameters parameter : parameters) {
            super.<Parameters>addEntity(parameter);
        }
    }

    protected CreditCardEntity getById(Long id) throws DbException {
        Objects tempObject = super.<Objects, Long>getByIdEntity(id, Objects.class);
        List<Parameters> parameters = getParametersList(tempObject);
        CreditCardEntity creditCardEntity = new CreditCardEntity(parameters);
        List<ObjReference> objReferencesOfReference = getObjReferencesOfReference(tempObject);
        creditCardEntity.setCurrencyId(objReferencesOfReference.get(0).getObjReferenceId().getObject().getObjectId());
        creditCardEntity.setId(id);
        return creditCardEntity;
    }

    protected void update(Long id, CreditCardEntity creditCardEntity) throws SQLException, DbException {
        Objects tempObject = super.<Objects, Long>getByIdEntity(id, Objects.class);
        List<Parameters> currentParameters = getParametersList(tempObject);
        List<ParametersId> parametersIds = new ArrayList<ParametersId>();
        for(int i = 0; i < TablesInfo.getCreditCardCountOfEntityAttributes(); i++) {
            parametersIds.add(currentParameters.get(i).getParametersId());
        }
        List<Parameters> newParameters = creditCardEntity.getParametersByParametersId(parametersIds);
        for (int i = 0; i < TablesInfo.getCreditCardCountOfEntityAttributes(); i++) {
            currentParameters.get(i).setValue(newParameters.get(i).getValue());
            currentParameters.get(i).setDateValue(newParameters.get(i).getDateValue());
        }
        for (Parameters currentParameter : currentParameters) {
            super.<Parameters>updateEntity(currentParameter);
        }
    }

    protected List<CreditCardEntity> getAll() throws DbException {
        List<CreditCardEntity> allCreditCards = new ArrayList<CreditCardEntity>();
        List<Objects> listOfObjects = getAllObjectsByObjTypeId(TablesInfo.getCreditCardObjectTypeId());
        for (int i = 0; i < listOfObjects.size(); i++) {
            Objects tempObject = listOfObjects.get(i);
            List<Parameters> tempParametersList = getParametersList(tempObject);
            CreditCardEntity creditCardEntity = new CreditCardEntity(tempParametersList);
            List<ObjReference> objReferencesOfReference = getObjReferencesOfReference(tempObject);
            creditCardEntity.setCurrencyId(objReferencesOfReference.get(0).getObjReferenceId().getObject().getObjectId());
            creditCardEntity.setId(tempObject.getObjectId());
            allCreditCards.add(creditCardEntity);
        }
        return allCreditCards;
    }
}
