package com.payment_cracker.api.dao.middle_level.middle_entities;


import com.payment_cracker.api.dao.utils.TablesInfo;
import com.payment_cracker.api.dao.basic_level.basic_entities.Attributes;
import com.payment_cracker.api.dao.basic_level.basic_entities.Objects;
import com.payment_cracker.api.dao.basic_level.basic_entities.Parameters;
import com.payment_cracker.api.dao.basic_level.basic_entities.ParametersId;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CurrencyEntity implements Entity {
    private Long currencyId;
    private String currencyLabel;
    private Double currencyValue;
    private Date currencyDate;

    public CurrencyEntity() {
    }

    public CurrencyEntity(List<Parameters> parameters) {
        this(parameters.get(0).getValue(), Double.valueOf(parameters.get(1).getValue()), parameters.get(2).getDateValue());
    }

    public CurrencyEntity(String currencyLabel, Double currencyValue, Date currencyDate) {
        setFields(currencyLabel, currencyValue, currencyDate);
    }

    public void setFields(String currencyLabel, Double currencyValue, Date currencyDate) {
        this.currencyLabel = currencyLabel;
        setFieldsWithoutLabel(currencyValue, currencyDate);
    }

    public void setFieldsWithoutLabel(Double currencyValue, Date currencyDate) {
        this.currencyValue = currencyValue;
        this.currencyDate = currencyDate;
    }

    public List<Parameters> getParameters(Objects object, List<Attributes> attributes) {
        List<ParametersId> parametersIds = new ArrayList<ParametersId>();
        for(int i = 0; i < TablesInfo.getCurrencyCountOfEntityAttributes(); i++) {
            parametersIds.add(new ParametersId(object, attributes.get(i)));
        }
        return getParametersByParametersId(parametersIds);
    }

    public List<Parameters> getParametersByParametersId(List<ParametersId> parametersIds) {
        List<Parameters> parameters = new ArrayList<Parameters>();
        for(int i = 0; i < TablesInfo.getCurrencyCountOfEntityAttributes(); i++) {
            parameters.add((new Parameters()));
            parameters.get(i).setParametersId(parametersIds.get(i));
        }
        parameters.get(0).setValue(this.currencyLabel);
        parameters.get(1).setValue(String.valueOf(this.currencyValue));
        parameters.get(2).setDateValue(this.currencyDate);
        return parameters;
    }

    public Long getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(Long currencyId) {
        this.currencyId = currencyId;
    }

    public String getCurrencyLabel() {
        return currencyLabel;
    }

    public void setCurrencyLabel(String currencyLabel) {
        this.currencyLabel = currencyLabel;
    }

    public Double getCurrencyValue() {
        return currencyValue;
    }

    public void setCurrencyValue(Double currencyValue) {
        this.currencyValue = currencyValue;
    }

    public Date getCurrencyDate() {
        return currencyDate;
    }

    public void setCurrencyDate(Date currencyDate) {
        this.currencyDate = currencyDate;
    }

    @Override
    public String toString() {
        return "CurrencyEntity{" +
                "currencyLabel='" + currencyLabel + '\'' +
                ", currencyValue=" + currencyValue +
                ", currencyDate='" + currencyDate + '\'' +
                '}';
    }
}
