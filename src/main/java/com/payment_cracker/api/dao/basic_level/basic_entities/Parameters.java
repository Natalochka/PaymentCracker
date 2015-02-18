package com.payment_cracker.api.dao.basic_level.basic_entities;

import javax.persistence.*;
import java.util.Date;


/**
 * Created by Александр on 11/20/2014.
 */
@Entity
@Table(name = "Parameters")
public class Parameters implements  HibernateTableInterface{

    @EmbeddedId
    private ParametersId parametersId;

    @Column(name = "value")
    private String value;

    @Column(name = "dateValue")
    private Date dateValue;

    public Parameters() {
    }

    public Parameters(ParametersId parametersId, String value, Date dateValue) {

        this.parametersId = parametersId;
        this.value = value;
        this.dateValue = dateValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Parameters that = (Parameters) o;

        if (dateValue != null ? !dateValue.equals(that.dateValue) : that.dateValue != null) return false;
        if (parametersId != null ? !parametersId.equals(that.parametersId) : that.parametersId != null) return false;
        if (value != null ? !value.equals(that.value) : that.value != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = parametersId != null ? parametersId.hashCode() : 0;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        result = 31 * result + (dateValue != null ? dateValue.hashCode() : 0);
        return result;
    }

    public Parameters(String value, Date date) {
        this.value = value;
        this.dateValue = date;
    }

    public ParametersId getParametersId() {
        return parametersId;
    }

    public void setParametersId(ParametersId parametersId) {
        this.parametersId = parametersId;
    }


    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Date getDateValue() {
        return dateValue;
    }

    public void setDateValue(Date dateValue) {
        this.dateValue = dateValue;
    }
}
