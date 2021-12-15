package com.example.application.data.entity;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.Formula;

@Entity
public class Company {

    @Id
    @GeneratedValue
    private Integer id;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
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
        Company other = (Company) obj;
        return Objects.equals(id, other.id);
    }

    @Override
    public String toString() {
        return "Company [employeeCount=" + employeeCount + ", id=" + id + ", name=" + name + "]";
    }

}
