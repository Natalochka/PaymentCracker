package com.payment_cracker.api.dao.utils;


import static com.payment_cracker.api.dao.utils.AttributesInfo.*;

/**
 * Created by Александр on 01.02.2015.
 */
public enum Purse implements ContractsInterface {
    BALANCE(ATTR_BALANCE_OF_PURSE, "PURSE_BALANCE", AttributeTypes.DOUBLE),
    CURRENCY_ID(ATTR_CURRENCY_ID_OF_PURSE, "PURSE_TO_CURRENCY", AttributeTypes.LONG);;

    private AttributeTypes attributeType;
    private Integer attributeId;
    private String columnName;

    private Purse(Integer attributeId, String columnName, AttributeTypes attributeType) {
        this.attributeId = attributeId;
        this.columnName = columnName;
        this.attributeType = attributeType;
    }

    @Override
    public Integer getAttributeId() {
        return attributeId;
    }

    @Override
    public String getColumnName() {
        return columnName;
    }

    @Override
    public AttributeTypes getAttributeType() {
        return attributeType;
    }

}
