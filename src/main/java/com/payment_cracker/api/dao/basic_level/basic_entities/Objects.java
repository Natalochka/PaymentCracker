package com.payment_cracker.api.dao.basic_level.basic_entities;

//import org.hibernate.search.annotations.IndexedEmbedded;

import javax.persistence.*;

/**
 * Created by Александр on 11/20/2014.
 */
@Entity
@Table(name = "Objects")
public class Objects implements  HibernateTableInterface{

    @Id
    @Column(name = "objectId")
    private Long objectId;

    @ManyToOne
    @JoinColumn(name = "parentId")
    private Objects parent;

    @ManyToOne
    @JoinColumn(name = "objTypeId", nullable = false)
    private ObjType objType;

    @Column(name = "name")
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Objects objects = (Objects) o;

        if (name != null ? !name.equals(objects.name) : objects.name != null) return false;
        if (objType != null ? !objType.equals(objects.objType) : objects.objType != null) return false;
        if (objectId != null ? !objectId.equals(objects.objectId) : objects.objectId != null) return false;
        if (parent != null ? !parent.equals(objects.parent) : objects.parent != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = objectId != null ? objectId.hashCode() : 0;
        result = 31 * result + (parent != null ? parent.hashCode() : 0);
        result = 31 * result + (objType != null ? objType.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    public Objects() {

    }

    public Objects(Long objectId, ObjType objType, String name) {
        this.objectId = objectId;
        this.objType = objType;
        this.name = name;
    }
    public Objects(Long objectId, ObjType objType, String name, Objects parent) {
        this.objectId = objectId;
        this.objType = objType;
        this.name = name;
        this.parent = parent;
    }


    public Long getObjectId() {
        return objectId;
    }

    public Objects setObjectId(Long objectId) {
        this.objectId = objectId;
        return this;
    }


    public Objects getParent() {
        return parent;
    }

    public Objects setParent(Objects parent) {
        this.parent = parent;
        return this;
    }


    public ObjType getObjectType() {
        return objType;
    }

    public Objects setObjectType(ObjType objType) {
        this.objType = objType;
        return this;
    }


    public String getName() {
        return name;
    }

    public Objects setName(String name) {
        this.name = name;
        return this;
    }
}
