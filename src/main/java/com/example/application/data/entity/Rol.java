package com.example.application.data.entity;

import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;

@Entity
public class Rol extends AbstractEntity {

    @NotEmpty
    private String name;

    /* ** Creamos constructor ** */

    public Rol() {
    }

    public Rol(String name) {
        this.name = name;
    }

    /* ** Creamos constructor ** */

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Rol [id=" + this.getId() + ", name=" + name + "]";
    }

}
