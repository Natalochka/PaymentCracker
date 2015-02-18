package com.payment_cracker.api.dao.basic_level.utils;


public class QueryRepository {
    public static final String SELECT_PARAMETERS_BY_OBJECTS = "select par.* from objects obj, parameters par where obj.objectId = ? and par.objectId = ?";
    public static final String SELECT_ATTRIBUTES_BY_OBJTYPE = " select * from ATTRIBUTES attr where objTypeId = ?";
    public static final String SELECT_ALL_OBJECTS_BY_TYPE = " select * from OBJECTS where ObjTypeId = ?";
    public static final String SELECT_ALL_ENTITIES_BY_TYPE = "Select userPar.* From Objects users, Parameters userPar Where  users.OBJTYPEID = ? AND userPar.OBJECTID = users.OBJECTID \n" +
            "ORDER BY userPar.OBJECTID";
    public static final String SELECT_OBJREFERENCES_BY_REFERENCE = "select * from objreference where reference = ?";
    public static final String addPurse = "";
    public static final String updatePurse = "";
    public static final String deletePurse = "";
    public static final String getPurse = "";
    public static final String addCurrency = "";
    public static final String updateCurrency = "";
    public static final String deleteCurrency = "";
    public static final String getCurrency = "";
    public static final String addCreditCard = "";
    public static final String updateCreditCard = "";
    public static final String deleteCreditCard = "";
    public static final String getCreditCard = "";
    public static final String addTransactions = "";
    public static final String updateTransactions = "";
    public static final String deleteTransactions = "";
    public static final String getTransactions = "";
    public static final String checkIsUserUnique = "";
}
