package com.example.application.backend.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Projecto extends AbstractEntity {

    @Column(name = "nombre", length = 30, nullable = false, unique = true)
    private String name;

    /*********** CREAMOS LAS RELACIONES ENTRE TABLAS **************/
    /* AL USAR RELACIONES UNIDIRECCIONALES NO LAS DECLARAMOS */

    // Creamos la relación ManyToMany débil, el mappedBy apunta al nombre del objeto
    // creado para relacionarse
    // @ManyToMany(mappedBy = "projectos")
    // private List<User> userList; // la creamos de tipo List ya que apunta a
    // "Many"
    // Debemos crear los métodos Get y Set (ver más abajo)

    /******** DECLARAMOS LOS CONTROLADORES, GETTER Y SETTER **********/

    public Projecto() {
    }

    public Projecto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Projecto [id=" + this.getId() + ", name=" + name + "]";
    }

}
