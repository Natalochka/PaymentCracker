package com.payment_cracker.api.dao.utils;

import static com.payment_cracker.api.dao.utils.AttributesInfo.*;

/**
 * Created by Александр on 01.02.2015.
 */
public enum Messages implements ContractsInterface {
    DATE(ATTR_DATE_OF_MESSAGE, "MESSAGE_DATE", AttributeTypes.DATE),
    TEXT(ATTR_TEXT_OF_MESSAGE, "MESSAGE_TEXT", AttributeTypes.STRING),
    CONFIRM_TOKEN(ATTR_CONFIRM_TOKEN_OF_MESSAGE, "CONFIRM_TOKEN_MESSAGE", AttributeTypes.BOOLEAN);

    private AttributeTypes attributeType;
    private Integer attributeId;
    private String columnName;

    private Messages(Integer attributeId, String columnName, AttributeTypes attributeType) {
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
