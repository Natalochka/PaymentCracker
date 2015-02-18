package com.payment_cracker.api.dao.basic_level.basic_entities;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

/**
 * Created by Александр on 1/6/2015.
 */
@Embeddable
//@Indexed
public class ObjReferenceId implements Serializable {

    @ManyToOne
    @JoinColumn(name = "attrId", nullable = false)
    private Attributes attr;

    @ManyToOne
    @JoinColumn(name = "objectId", nullable = false)
    private Objects object;

    @ManyToOne
    @JoinColumn(name = "reference", nullable = false)
    private Objects reference;

    public ObjReferenceId() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ObjReferenceId that = (ObjReferenceId) o;

        if (attr != null ? !attr.equals(that.attr) : that.attr != null) return false;
        if (object != null ? !object.equals(that.object) : that.object != null) return false;
        if (reference != null ? !reference.equals(that.reference) : that.reference != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = attr != null ? attr.hashCode() : 0;
        result = 31 * result + (object != null ? object.hashCode() : 0);
        result = 31 * result + (reference != null ? reference.hashCode() : 0);
        return result;
    }

    public ObjReferenceId(Attributes attr, Objects object, Objects reference) {
        this.attr = attr;
        this.object = object;
        this.reference = reference;
    }

    public void setAttr(Attributes attr) {
        this.attr = attr;
    }

    public Attributes getAttr() {
        return attr;
    }

    public void setObject(Objects object) {
        this.object = object;
    }

    public Objects getObject() {
        return object;
    }

    public Objects getReference() {
        return reference;
    }

    public void setReference(Objects reference) {
        this.reference = reference;
    }
}
