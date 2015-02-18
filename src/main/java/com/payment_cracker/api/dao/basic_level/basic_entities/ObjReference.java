package com.payment_cracker.api.dao.basic_level.basic_entities;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Александр on 11/20/2014.
 */
@Entity
@Table(name = "ObjReference")
public class ObjReference implements  HibernateTableInterface{
    @EmbeddedId
    private ObjReferenceId objReferenceId;

    public ObjReference() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ObjReference that = (ObjReference) o;

        if (objReferenceId != null ? !objReferenceId.equals(that.objReferenceId) : that.objReferenceId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        return objReferenceId != null ? objReferenceId.hashCode() : 0;
    }

    public ObjReference(ObjReferenceId objReferenceId) {
        this.objReferenceId = objReferenceId;
    }

    public ObjReferenceId getObjReferenceId() {
        return objReferenceId;
    }

    public void setObjReferenceId(ObjReferenceId objReferenceId) {
        this.objReferenceId = objReferenceId;
    }

}
