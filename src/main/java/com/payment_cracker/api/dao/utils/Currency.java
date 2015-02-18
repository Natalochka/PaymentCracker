package com.payment_cracker.api.dao.utils;


import static com.payment_cracker.api.dao.utils.AttributesInfo.*;

/**
 * Created by Александр on 01.02.2015.
 */
public enum Currency implements ContractsInterface {
    CURRENCY_LABEL(ATTR_LABEL_OF_CURRENCY, "CURRENCY_LABEL", AttributeTypes.STRING),
    CURRENCY_VALUE(ATTR_VALUE_OF_CURRENCY, "CURRENCY_VALUE", AttributeTypes.DOUBLE),
    CURRENCY_DATE(ATTR_DATE_OF_CURRENCY, "CURRENCY_DATE", AttributeTypes.DATE);

    private AttributeTypes attributeType;
    private Integer attributeId;
    private String columnName;

    private Currency(Integer attributeId, String columnName, AttributeTypes attributeType) {
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
