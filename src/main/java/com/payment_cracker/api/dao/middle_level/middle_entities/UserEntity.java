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
public class UserEntity implements Entity {
    private Long id;
    private String login;
    private String password;
    private String FIO;
    private String phoneNumber;
    private String email;
    private Date regDate;
    private boolean active;
    private boolean ban;
    private boolean administrator;

    public UserEntity() {
    }

    public UserEntity(List<Parameters> parameters, Objects name) {
        this(name.getName(),
                parameters.get(0).getValue(),
                parameters.get(1).getValue(),
                parameters.get(2).getValue(),
                parameters.get(3).getValue(),
                parameters.get(4).getDateValue(),
                Boolean.valueOf(parameters.get(5).getValue()),
                Boolean.valueOf(parameters.get(6).getValue()),
                Boolean.valueOf(parameters.get(7).getValue()));
    }

    public UserEntity(String login, String password, String FIO, String phoneNumber, String email, Date regDate, boolean active, boolean ban, boolean administrator) {
        setFields(login, password, FIO, phoneNumber, email, regDate, active, ban, administrator);
    }

    public void setFields(String login, String password, String FIO, String phoneNumber, String email, Date regDate, boolean active, boolean ban, boolean administrator) {
        this.login = login;
        setFieldsWithoutLogin(password, FIO, phoneNumber, email, regDate, active, ban, administrator);
    }

    public void setFieldsWithoutLogin(String password, String FIO, String phoneNumber, String email, Date regDate, boolean active, boolean ban, boolean administrator) {
        this.password = password;
        this.FIO = FIO;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.regDate = regDate;
        this.active = active;
        this.ban = ban;
        this.administrator = administrator;
    }

    public List<Parameters> getParameters(Objects object, List<Attributes> attributes) {
        List<ParametersId> parametersIds = new ArrayList<ParametersId>();
        for (int i = 0; i < TablesInfo.getUsersCountOfEntityAttributes(); i++) {
            if(attributes.get(i).getType() != AttributeTypes.LONG.getTypeId()) {
                parametersIds.add(new ParametersId(object, attributes.get(i)));
            }
        }
        return getParametersByParametersId(parametersIds);
    }

    public List<Parameters> getParametersByParametersId(List<ParametersId> parameterIds) {
        List<Parameters> parameters = new ArrayList<Parameters>();
        for(int i = 0; i < TablesInfo.getUsersCountOfEntityAttributes(); i++) {
            parameters.add(new Parameters());
            parameters.get(i).setParametersId(parameterIds.get(i));
        }
        parameters.get(0).setValue(this.password);
        parameters.get(1).setValue(this.FIO);
        parameters.get(2).setValue(this.phoneNumber);
        parameters.get(3).setValue(this.email);
        parameters.get(4).setDateValue(this.regDate);
        parameters.get(5).setValue(String.valueOf(this.active));
        parameters.get(6).setValue(String.valueOf(this.ban));
        parameters.get(7).setValue(String.valueOf(this.administrator));
        return parameters;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFIO() {
        return FIO;
    }

    public void setFIO(String FIO) {
        this.FIO = FIO;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isBan() {
        return ban;
    }

    public void setBan(boolean ban) {
        this.ban = ban;
    }

    public boolean isAdministrator() {
        return administrator;
    }

    public void setAdministrator(boolean administrator) {
        this.administrator = administrator;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", FIO='" + FIO + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", regDate='" + regDate + '\'' +
                ", active=" + active +
                ", ban=" + ban +
                ", administrator=" + administrator +
                '}';
    }
}
