package com.payment_cracker.api.dao.basic_level.basic_entities;

import javax.persistence.*;

/**
 * Created by Александр on 11/20/2014.
 */
@Entity
@Table(name = "ObjType")
public class ObjType implements  HibernateTableInterface{
    @Id
    @Column(name = "objTypeId")
    private Integer objTypeId;

    @ManyToOne
    @JoinColumn(name = "parentId" )
    private ObjType parentId;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    public ObjType() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ObjType objType = (ObjType) o;

        if (name != null ? !name.equals(objType.name) : objType.name != null) return false;
        if (objTypeId != null ? !objTypeId.equals(objType.objTypeId) : objType.objTypeId != null) return false;
        if (parentId != null ? !parentId.equals(objType.parentId) : objType.parentId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = objTypeId != null ? objTypeId.hashCode() : 0;
        result = 31 * result + (parentId != null ? parentId.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    public ObjType(Integer objTypeId, String name) {
        this.objTypeId = objTypeId;
        this.name = name;
    }

    public Integer getObjectTypeId() {
        return objTypeId;
    }
    public ObjType setObjectTypeId(Integer objTypeId) {
        this.objTypeId = objTypeId;
        return this;
    }

    public ObjType getParentId() {
        return parentId;
    }
    public ObjType setParentId(ObjType parentId) {
        this.parentId = parentId;
        return this;
    }

    public String getName() {
        return name;
    }
    public ObjType setName(String name) {
        this.name = name;
        return this;
    }
}
