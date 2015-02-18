package com.payment_cracker.api.dao.basic_level.basic_entities;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

/**
 * Created by Александр on 1/6/2015.
 */

@Embeddable
public class ParametersId implements Serializable {

    @ManyToOne
    @JoinColumn(name = "attrId", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Attributes attr;

    @ManyToOne
    @JoinColumn(name = "objectId", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Objects object;

    public  ParametersId(){
    }

    public ParametersId(Objects object, Attributes attr) {
        this.object = object;
        this.attr = attr;
    }

    public Attributes getAttribute() {
        return attr;
    }

    public void setAttribute(Attributes attr) {
        this.attr = attr;
    }

    public void setObject(Objects object) {
        this.object = object;
    }

    public Objects getObject() {
        return object;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ParametersId that = (ParametersId) o;

        if (attr != null ? !attr.equals(that.attr) : that.attr != null) return false;
        if (object != null ? !object.equals(that.object) : that.object != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = attr != null ? attr.hashCode() : 0;
        result = 31 * result + (object != null ? object.hashCode() : 0);
        return result;
    }
}
