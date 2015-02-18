package com.payment_cracker.api.dao.utils;

/**
 * Created by Александр on 01.02.2015.
 */
public enum Users implements ContractsInterface {
    //LOGIN(Objects.class, 0, "LOGIN", AttributeTypes.STRING),
    PASSWORD(1, "PASSWORD", AttributeTypes.STRING),
    FIO(2, "FIO", AttributeTypes.STRING),
    PHONE_NUMBER(3, "PHONE_NUMBER", AttributeTypes.STRING),
    EMAIL(4, "EMAIL", AttributeTypes.STRING),
    REGISTRATION_DATE(5, "REGISTRATION_DATE", AttributeTypes.DATE),
    IS_ACTIVE(6, "IS_ACTIVE", AttributeTypes.BOOLEAN),
    IS_BANNED(7, "IS_BANNED", AttributeTypes.BOOLEAN),
    IS_ADMINISTRATOR(8, "IS_ADMINISTRATOR", AttributeTypes.BOOLEAN);


    private AttributeTypes attributeType;
    private Integer attributeId;
    private String columnName;

    private Users(Integer attributeId, String columnName, AttributeTypes attributeType) {
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
