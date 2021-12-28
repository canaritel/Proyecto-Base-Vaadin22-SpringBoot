package com.example.application.backend.data.entity;

import javax.persistence.Entity;

@Entity
public class Status extends AbstractEntity {

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

    @Override
    public String toString() {
        return "Status [id=" + this.getId() + ", name=" + name + "]";
    }

}
