package com.gene.dao;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "gene")
public class GeneEntity implements java.io.Serializable{
    private int id;
    private String name;
    private String describetion;

    @Id
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "describetion")
    public String getDescribetion() {
        return describetion;
    }

    public void setDescribetion(String describetion) {
        this.describetion = describetion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GeneEntity that = (GeneEntity) o;
        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(describetion, that.describetion);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, describetion);
    }
}
