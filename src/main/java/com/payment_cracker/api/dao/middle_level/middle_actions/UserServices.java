package com.payment_cracker.api.dao.middle_level.middle_actions;


import com.payment_cracker.api.dao.basic_level.basic_dao.BasicDao;
import com.payment_cracker.api.dao.basic_level.basic_entities.*;
import com.payment_cracker.api.dao.exceptions.DbException;
import com.payment_cracker.api.dao.middle_level.middle_entities.UserEntity;
import com.payment_cracker.api.dao.utils.SessionManager;
import com.payment_cracker.api.dao.utils.TablesInfo;
import org.hibernate.Session;
import org.hibernate.jdbc.ReturningWork;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class UserServices extends BasicDao { // сделать final
    private SessionManager sessionManager;
    private Session session;

    public UserServices(SessionManager sessionManager) {
        super(sessionManager.getSession());
        this.sessionManager = sessionManager;
        session = sessionManager.getSession();
    }

    public SessionManager getSessionManager() {
        return sessionManager;
    }

    protected void add(UserEntity entity) throws DbException {
        Long objectId = entity.getId();
        entity.setId(objectId);
        Objects tempObject = new Objects();
        tempObject.setObjectId(objectId);
        tempObject.setName(entity.getLogin());
        tempObject.setObjectType(new ObjType().setObjectTypeId(TablesInfo.getUsersObjTypeId()));
        List<Attributes> attributes = getAttributesList(TablesInfo.getUsersObjTypeId());
        super.<Objects>addEntity(tempObject);
        List<Parameters> parameters = entity.getParameters(tempObject, attributes);
        for (Parameters parameter : parameters) {
            super.<Parameters>addEntity(parameter);
        }
    }

    protected void update(Long id, UserEntity userEntity) throws DbException {
        Objects tempObject = super.<Objects, Long>getByIdEntity(id, Objects.class);
        tempObject.setName(userEntity.getLogin());
        super.<Objects>updateEntity(tempObject);
        List<Parameters> currentParameters = getParametersList(tempObject);
        List<ParametersId> parametersIds = new ArrayList<ParametersId>();
        for(int i = 0; i < TablesInfo.getUsersCountOfEntityAttributes(); i++) {
            parametersIds.add(currentParameters.get(i).getParametersId());
        }
        List<Parameters> newParameters = userEntity.getParametersByParametersId(parametersIds);
        for (int i = 0; i < TablesInfo.getUsersCountOfEntityAttributes(); i++) {
            currentParameters.get(i).setValue(newParameters.get(i).getValue());
            currentParameters.get(i).setDateValue(newParameters.get(i).getDateValue());
        }
        for (Parameters currentParameter : currentParameters) {
            super.<Parameters>updateEntity(currentParameter);
        }
    }

    protected UserEntity getById(Long id) throws DbException {
        Objects objects = super.<Objects, Long>getByIdEntity(id, Objects.class);
        List<Parameters> parameters = getParametersList(objects);
        UserEntity userEntity = new UserEntity(parameters, objects);
        userEntity.setId(id);
        return userEntity;
    }

    protected List<UserEntity> getAll() throws DbException {
        List<UserEntity> allUsers = new ArrayList<UserEntity>();
        List<Objects> listOfObjects = getAllObjectsByObjTypeId(TablesInfo.getUsersObjTypeId());
        if(listOfObjects != null) {
        for (int i = 0; i < listOfObjects.size(); i++) {
            Objects object = listOfObjects.get(i);
            List<Parameters> tempParametersList = getParametersList(object);
            allUsers.add(new UserEntity(tempParametersList, object));
            allUsers.get(i).setId(object.getObjectId());
        }}
        return allUsers;
    }

    protected Long checkIsUserUnique(String login, String password) {
        return sessionManager.getSession().doReturningWork(new ReturningWork<Long>() {
            public String login;
            public String password;
            public Long result;

            public ReturningWork<Long> setParameters(String login, String password) {
                this.login = login;
                this.password = password;
                return this;
            }

            @Override
            public Long execute(Connection connection) throws SQLException {
                CallableStatement call = connection.prepareCall("{ ? = call checkIsUserUnique(?,?) }");
                call.registerOutParameter(1, Types.LONGNVARCHAR); // or whatever it is
                call.setString(2, login);
                call.setString(3, password);
                call.execute();
                Long id = call.getLong(1);
                call.close();
                return id;
            }
        }.setParameters(login, password));
    }
}
