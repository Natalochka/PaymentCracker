package com.payment_cracker.api.dao.utils;

import static com.payment_cracker.api.dao.utils.AttributesInfo.ATTR_BALANCE_OF_CREDIT_CARD;
import static com.payment_cracker.api.dao.utils.AttributesInfo.ATTR_CURRENCY_ID_OF_CREDIT_CARD;

/**
 * Created by Александр on 01.02.2015.
 */
public enum CreditCard implements ContractsInterface {
    BALANCE(ATTR_BALANCE_OF_CREDIT_CARD, "CREDIT_CARD_BALANCE", AttributeTypes.DOUBLE),
    CURRENCY_ID(ATTR_CURRENCY_ID_OF_CREDIT_CARD, "CREDIT_CARD_TO_CURRENCY", AttributeTypes.LONG);

    private AttributeTypes attributeType;
    private Integer attributeId;
    private String columnName;

    private CreditCard(Integer attributeId, String columnName, AttributeTypes attributeType) {
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
