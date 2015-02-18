package com.payment_cracker.api.dao.basic_level.basic_dao;


import com.payment_cracker.api.dao.basic_level.basic_entities.*;
import com.payment_cracker.api.dao.basic_level.utils.QueryRepository;
import com.payment_cracker.api.dao.exceptions.DbException;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Александр on 12/7/2014.
 */
public class BasicDao {
    protected static final Logger logger = Logger.getLogger("fill");

    private Session session;

    protected BasicDao(Session session) {
        this.session = session;
    }

    protected <T extends HibernateTableInterface> void addEntity(T entity) throws DbException {
        try {
            session.save(entity);
        } catch (HibernateException e) {
            throw new DbException(e);
        }
    }

    protected <T extends HibernateTableInterface> void updateEntity(T entity) throws DbException {
        try {
            session.update(entity);
        } catch (HibernateException e) {
            throw new DbException(e);
        }
    }


    protected <T extends HibernateTableInterface, E> T getByIdEntity(E id, Class clazz) throws DbException {
        T entity;
        try {
            entity = (T) session.get(clazz, (Serializable) id);
        } catch (HibernateException e) {
            throw new DbException(e);
        }
        return entity;
    }

    protected <T extends HibernateTableInterface> List<T> getAllEntities(Class clazz) throws DbException {
        List<T> entities;
        try {
            entities = session.createCriteria(clazz).list();
        }
        catch (HibernateException e) {
            throw new DbException(e);
        }
        return entities;
    }

    protected List<Parameters> getParametersList(Objects objects) {
        String id = objects.getObjectId().toString();
        Query que = session.createSQLQuery(QueryRepository.SELECT_PARAMETERS_BY_OBJECTS)
                .addEntity(Parameters.class)
                .setString(0, id)
                .setString(1, id);
        if(que.list()!=null && que.list().size()>0)
            return que.list();
        else return null;
    }

    protected List<Parameters> getAllParametersOfEntity(Integer typeId) {
        Query que = session.createSQLQuery(QueryRepository.SELECT_ALL_ENTITIES_BY_TYPE)
                .addEntity(Parameters.class)
                .setString(0, typeId.toString());
        if(que.list()!=null && que.list().size()>0)
            return que.list();
        else return null;
    }

    protected List<Objects> getAllObjectsByObjTypeId(Integer id) {
        Query que = session.createSQLQuery(QueryRepository.SELECT_ALL_OBJECTS_BY_TYPE)
                .addEntity(Objects.class)
                .setString(0, id.toString());
        if(que.list() != null && que.list().size()>0)
            return que.list();
        else return null;
    }

    protected List<Attributes> getAttributesList(Integer objTypeId) {
        Query que = session.createSQLQuery(QueryRepository.SELECT_ATTRIBUTES_BY_OBJTYPE)
                .addEntity(Attributes.class)
                .setString(0, String.valueOf(objTypeId));
        if(que.list() != null && que.list().size()>0)
            return que.list();
        else return null;
    }

    protected List<ObjReference> getObjReferencesOfReference(Objects objects)
    {
        String id = objects.getObjectId().toString();
        Query que = session.createSQLQuery(QueryRepository.SELECT_OBJREFERENCES_BY_REFERENCE)
                .addEntity(ObjReference.class)
                .setString(0,id);
        if(que.list()!=null && que.list().size()>0)
            return que.list();
        else return null;
    }
}
