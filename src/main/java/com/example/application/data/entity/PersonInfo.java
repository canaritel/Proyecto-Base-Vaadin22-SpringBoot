package com.example.application.data.entity;

import java.io.Serializable;

public class PersonInfo implements Serializable {
    private Integer id;
    private String name;

    public PersonInfo(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "PersonInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

}