package com.payment_cracker.api.dao.basic_level.basic_entities;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

/**
 * Created by Александр on 11/20/2014.
 */

@Entity
@Table(name = "Attributes")
public class Attributes implements  HibernateTableInterface {

    @Id
    @Column(name = "attrId")
    private Integer attrId;

    @ManyToOne
    @JoinColumn(name = "objTypeId", nullable = false)
    private ObjType objTypeId;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private Integer type;

    public Attributes() {
    }

    public Attributes(Integer attrId, ObjType objTypeId, Integer type, String name) {
        this.attrId = attrId;
        this.objTypeId = objTypeId;
        this.type = type;
        this.name = name;
    }

    public Integer getAttrId() {
        return attrId;
    }

    public ObjType getObjTypeId() {
        return objTypeId;
    }


    public String getName() {
        return name;
    }

    public Integer getType() {
        return type;
    }


    public Attributes setName(String name) {
        this.name = name;
        return  this;
    }

    public Attributes setType(Integer type) {
        this.type = type;
        return  this;
    }

    public Attributes setAttrId(Integer attr_id) {
        this.attrId = attr_id;
        return  this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Attributes that = (Attributes) o;

        if (attrId != null ? !attrId.equals(that.attrId) : that.attrId != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (objTypeId != null ? !objTypeId.equals(that.objTypeId) : that.objTypeId != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = attrId != null ? attrId.hashCode() : 0;
        result = 31 * result + (objTypeId != null ? objTypeId.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }

    public Attributes setObjTypeId(ObjType objTypeId) {
        this.objTypeId = objTypeId;
        return  this;
    }
}
