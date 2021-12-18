package com.example.application.data.entity;

import javax.persistence.Entity;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.Formula;

@Entity
public class Company extends AbstractEntity {

    @NotBlank // Más info: https://docs.oracle.com/javaee/7/tutorial/bean-validation001.htm ||
              // https://www.baeldung.com/javax-validation
    private String name;

    // @OneToMany(mappedBy = "company")
    // private List<Contact> employees = new ArrayList<>();

    @Formula("(select count(c.id) from Contact c where c.company_id = id)")
    private int employeeCount;

    @AssertTrue // Por defecto es verdadero
    boolean isActive;

    /* ** Creamos constructor ** */

    public Company() {
    }

    public Company(@NotBlank String name) {
        this.name = name;
    }

    /* ** Creamos getters y setters ** */

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEmployeeCount() {
        return employeeCount;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    @Override
    public String toString() {
        return "Company [employeeCount=" + employeeCount + ", id=" + this.getId() +
                ", name=" + name + "]";
    }

}
