package com.payment_cracker.api.dao.middle_level.action_interfaces;


import com.payment_cracker.api.dao.middle_level.middle_entities.CreditCardEntity;
import com.payment_cracker.api.dao.exceptions.DbException;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Александр on 1/13/2015.
 */
public interface CreditCardInterface {
    public void add(CreditCardEntity creditCardEntity) throws SQLException, DbException;
    public CreditCardEntity getById(Long id) throws DbException;
    public void update(Long id, CreditCardEntity creditCardEntity) throws SQLException, DbException;
    public List<CreditCardEntity> getAll() throws DbException;
}
