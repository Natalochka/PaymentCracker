package com.payment_cracker.api.dao.middle_level.middle_entities;

import com.payment_cracker.api.dao.utils.TablesInfo;
import com.payment_cracker.api.dao.basic_level.basic_entities.Attributes;
import com.payment_cracker.api.dao.basic_level.basic_entities.Objects;
import com.payment_cracker.api.dao.basic_level.basic_entities.Parameters;
import com.payment_cracker.api.dao.basic_level.basic_entities.ParametersId;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Александр on 1/13/2015.
 */
public class CreditCardEntity implements PaymentEntity {
    private Long id;
    private Double balance;
    private Long currencyId;

    public CreditCardEntity() {
    }

    public CreditCardEntity(List<Parameters> parameters) {
        this.balance = Double.valueOf(parameters.get(0).getValue());
    }

    public CreditCardEntity(Double balance, Long currencyId) {
        setFields(balance, currencyId);
    }

    public void setFields(Double balance, Long currencyId) {
        this.balance = balance;
        this.currencyId = currencyId;
    }

    public List<Parameters> getParameters(Objects object, List<Attributes> attributes) {
        List<ParametersId> parametersIds = new ArrayList<ParametersId>();
        for(int i = 0; i < TablesInfo.getCreditCardCountOfEntityAttributes(); i++) {
            parametersIds.add(new ParametersId(object, attributes.get(i)));
        }
        return getParametersByParametersId(parametersIds);
    }

    public List<Parameters> getParametersByParametersId(List<ParametersId> parametersIds) {
        List<Parameters> parameters = new ArrayList<Parameters>();
        for (int i = 0; i < TablesInfo.getCreditCardCountOfEntityAttributes(); i++) {
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

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Long getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(Long currencyId) {
        this.currencyId = currencyId;
    }
}
