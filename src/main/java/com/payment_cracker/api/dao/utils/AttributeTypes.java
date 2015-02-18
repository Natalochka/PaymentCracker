package com.payment_cracker.api.dao.utils;

/**
 * Created by Александр on 1/17/2015.
 */
public enum AttributeTypes {
    STRING(1),
    DATE(2),
    BOOLEAN(3),
    DOUBLE(4),
    LONG(5);
    private int typeId;
    AttributeTypes(int i) {
        this.typeId = i;
    }

    public int getTypeId() {
        return typeId;
    }
}
