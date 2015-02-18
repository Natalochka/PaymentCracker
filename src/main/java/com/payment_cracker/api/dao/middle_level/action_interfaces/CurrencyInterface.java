package com.payment_cracker.api.dao.middle_level.action_interfaces;


import com.payment_cracker.api.dao.middle_level.middle_entities.CurrencyEntity;
import com.payment_cracker.api.dao.exceptions.DbException;

import java.util.List;

/**
 * Created by Александр on 1/2/2015.
 */
public interface CurrencyInterface {
    public CurrencyEntity getById(Long id) throws DbException;
    public void add(CurrencyEntity currencyEntity) throws DbException;
    public void update(long id, CurrencyEntity currencyEntity) throws DbException;
    public void updateAllCurrencyByYahooAPI() throws DbException;
    public List<CurrencyEntity> getAll();
}

