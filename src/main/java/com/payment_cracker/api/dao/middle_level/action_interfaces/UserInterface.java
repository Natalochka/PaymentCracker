package com.payment_cracker.api.dao.middle_level.action_interfaces;


import com.payment_cracker.api.dao.exceptions.DbException;
import com.payment_cracker.api.dao.middle_level.middle_entities.UserEntity;

import java.util.List;

/**
 * Created by Александр on 12/26/2014.
 */
public interface UserInterface {
    public void add(UserEntity userEntity) throws DbException;
    public UserEntity getById(Long id) throws DbException;
    public void update(Long id, UserEntity userEntity) throws DbException;
    public List<UserEntity> getAll() throws DbException;
}
