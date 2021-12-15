package com.example.application.data.entity;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Status {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    /* ** Creamos constructor ** */

    public Status() {
    }

    public Status(String name) {
        this.name = name;
    }

    /* ** Creamos getters y setters ** */

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Status other = (Status) obj;
        return Objects.equals(id, other.id);
    }

    @Override
    public String toString() {
        return "Status [id=" + id + ", name=" + name + "]";
    }

}
