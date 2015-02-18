package com.payment_cracker.api.dao.middle_level.middle_entities;


import com.payment_cracker.api.dao.basic_level.basic_entities.Attributes;
import com.payment_cracker.api.dao.basic_level.basic_entities.Objects;
import com.payment_cracker.api.dao.basic_level.basic_entities.Parameters;
import com.payment_cracker.api.dao.basic_level.basic_entities.ParametersId;
import com.payment_cracker.api.dao.utils.AttributeTypes;
import com.payment_cracker.api.dao.utils.TablesInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Александр on 1/2/2015.
 */
public class PurseEntity implements PaymentEntity {
    private Long id;
    private Long userId;
    private Long currencyId;
    private double balance;

    public PurseEntity() {
    }

    public PurseEntity(List<Parameters> parameters, Objects purseName) {
        this.balance = Double.valueOf(parameters.get(0).getValue());
    }

    public PurseEntity(Long currencyId, Long userId, double balance) {
        setFields(currencyId, userId, balance);
    }

    public void setFields(Long currencyId, Long userId, double balance) {
        setFieldsWithoutPurseName(currencyId, userId, balance);
    }

    public void setFieldsWithoutPurseName(Long currencyId, Long userId, double balance) {
        this.currencyId = currencyId;
        this.userId = userId;
        this.balance = balance;
    }

    public List<Parameters> getParameters(Objects object, List<Attributes> attributes) {
        List<ParametersId> parametersIds = new ArrayList<ParametersId>();
        List<Attributes> tempAttributes = new ArrayList<>();
        for (Attributes attribute : attributes) {
            if(attribute.getType() != AttributeTypes.LONG.getTypeId()) {
                tempAttributes.add(attribute);
            }
        }
        for(int i = 0; i < TablesInfo.getPurseCountOfEntityAttributes(); i++) {
            parametersIds.add(new ParametersId(object, tempAttributes.get(i)));
        }
        return getParametersByParametersId(parametersIds);
    }

    public List<Parameters> getParametersByParametersId(List<ParametersId> parametersIds) {
        List<Parameters> parameters = new ArrayList<Parameters>();
        for (int i = 0; i < TablesInfo.getPurseCountOfEntityAttributes(); i++) {
            parameters.add(new Parameters());
            parameters.get(i).setParametersId(parametersIds.get(i));
        }
        parameters.get(0).setValue(String.valueOf(this.balance));
        return parameters;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(Long currencyId) {
        this.currencyId = currencyId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
