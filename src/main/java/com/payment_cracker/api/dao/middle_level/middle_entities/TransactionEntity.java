package com.payment_cracker.api.dao.middle_level.middle_entities;

import com.payment_cracker.api.dao.basic_level.basic_entities.Attributes;
import com.payment_cracker.api.dao.basic_level.basic_entities.Objects;
import com.payment_cracker.api.dao.basic_level.basic_entities.Parameters;
import com.payment_cracker.api.dao.basic_level.basic_entities.ParametersId;
import com.payment_cracker.api.dao.utils.AttributeTypes;
import com.payment_cracker.api.dao.utils.TablesInfo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Александр on 1/2/2015.
 */
public class TransactionEntity<T extends PaymentEntity, T2 extends PaymentEntity> implements Entity {
    private Long id;
    private T sender;
    private T2 receiver;
    private Double money;
    private Double senderCurrencyValue;
    private Double receiverCurrencyValue;
    private Date date;
    
    public TransactionEntity() {
    }

    public TransactionEntity(T sender, T2 receiver, Double money) {
        this.sender = sender;
        this.receiver = receiver;
        this.money = money;
    }

    public TransactionEntity(T sender, T2 receiver, Double money, Double senderCurrencyValue, Double receiverCurrencyValue, Date date) {
        setFields(sender, receiver, money, senderCurrencyValue, receiverCurrencyValue, date);
    }

    public TransactionEntity (List<Parameters> parametersList){
        this.money = Double.valueOf(parametersList.get(0).getValue());
        this.senderCurrencyValue = Double.valueOf(parametersList.get(1).getValue());
        this.receiverCurrencyValue = Double.valueOf(parametersList.get(2).getValue());
        this.date = parametersList.get(3).getDateValue();
    }

    public void setFields(T sender, T2 receiver, Double money, Double senderCurrencyValue, Double receiverCurrencyValue, Date date) {

        this.sender = sender;
        this.receiver = receiver;
        this.money = money;
        this.senderCurrencyValue = senderCurrencyValue;
        this.receiverCurrencyValue = receiverCurrencyValue;
        this.date = date;
    }

    public List<Parameters> getParameters(Objects object, List<Attributes> attributes) {
        List<ParametersId> parametersIds = new ArrayList<ParametersId>();
        List<Attributes> tempAttributes = new ArrayList<>();
        for (Attributes attribute : attributes) {
            if(attribute.getType() != AttributeTypes.LONG.getTypeId()) {
                tempAttributes.add(attribute);
            }
        }
        for(int i = 0; i < TablesInfo.getTransactionInfoCountOfEntityAttributes(); i++) {
            parametersIds.add(new ParametersId(object, tempAttributes.get(i)));
        }
        return getParametersByParametersId(parametersIds);
    }

    public List<Parameters> getParametersByParametersId(List<ParametersId> parametersIds) {
        List<Parameters> parameters = new ArrayList<Parameters>();
        for (int i = 0; i < TablesInfo.getTransactionInfoCountOfEntityAttributes(); i++) {
            parameters.add(new Parameters());
            parameters.get(i).setParametersId(parametersIds.get(i));
        }
        parameters.get(0).setValue(String.valueOf(this.money));
        parameters.get(1).setValue(String.valueOf(this.senderCurrencyValue));
        parameters.get(2).setValue(String.valueOf(this.receiverCurrencyValue));
        parameters.get(3).setDateValue(this.date);
        return parameters;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public T getSender() {
        return sender;
    }

    public void setSender(T sender) {
        this.sender = sender;
    }

    public T2 getReceiver() {
        return receiver;
    }

    public void setReceiver(T2 receiver) {
        this.receiver = receiver;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Double getSenderCurrencyValue() {
        return senderCurrencyValue;
    }

    public void setSenderCurrencyValue(Double senderCurrencyValue) {
        this.senderCurrencyValue = senderCurrencyValue;
    }

    public Double getReceiverCurrencyValue() {
        return receiverCurrencyValue;
    }

    public void setReceiverCurrencyValue(Double receiverCurrencyValue) {
        this.receiverCurrencyValue = receiverCurrencyValue;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}