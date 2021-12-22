package com.example.application.data.entity;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

@Entity
public class Person extends AbstractEntity {

    public enum Gender {
        HOMBRE, MUJER
    };

    @Size(min = 4, max = 50, message = "Debe tener entre 4 y 50 carácteres")
    @NotEmpty // Más info: https://docs.oracle.com/javaee/7/tutorial/bean-validation001.htm ||
              // https://www.baeldung.com/javax-validation
    private String name;

    @Size(min = 4, max = 50, message = "Debe tener entre 4 y 50 carácteres")
    @NotEmpty
    private String surname;

    @Email(message = "Email no valido")
    @Column(unique = true) // otra forma de validación
    private String email;

    private String telefono;

    @Past // The value of the field or property must be a date in the past
    private Date birthDate;

    private Gender gender;

    // *********** CREAMOS LAS RELACIONES ENTRE TABLAS **************/
    // *********** Recomendado hacer unicamente relaciones unidireccionables por
    // ************ mejora del mantenimiento BD *********

    /* ************************************************************* */
    // Creamos la relación ManyToOne unidireccional hacia la entidad Rol
    @JoinColumn(name = "FK_ROL") // creará esta FK
    @ManyToOne(optional = false, targetEntity = Rol.class) // no puede ser null
    private Rol roles; // Debemos crear los métodos Get y Set (ver más abajo)

    /* ************************************************************* */
    // Creamos la relación ManyToMany unidireccional hacia la entidad Company
    @JoinTable(name = "rel_person _projecto", // nombre a la relación de la tabla intermedia
            joinColumns = @JoinColumn(name = "FK_PERSON"), // primer FK
            inverseJoinColumns = @JoinColumn(name = "FK_PROJECTO")) // segundo FK
    @ManyToMany(targetEntity = Projecto.class, //
            fetch = FetchType.LAZY) // Many siempre de tipo Lazy, carga perozosa
    private Set<Projecto> projectos = new HashSet<>();
    // (fetch=FetchType.EAGER) penaliza en velocidad, ya que cargará todo el objeto.
    // Recomendable usar Lazy para menor carga de datos y dentro del Repositorio
    // crear un método de búsqueda con esa función.
    /* ************************************************************* */

    // Creamos la relación OneToOne unidireccional hacia la entidad deseada
    // @JoinColumn(name = "FK_ENTIDAD")
    // https://www.adictosaltrabajo.com/2020/04/02/hibernate-onetoone-onetomany-manytoone-y-manytomany/
    // @OneToMany(targetEntity = Entidad.class, cascade = CascadeType.ALL,
    // orphanRemoval = true)

    /* ************************************************************* */

    public Person() {
        this.birthDate = new Date();
    }

    // ---- CAMPOS OBLIGATORIOS DONDE CREO EL PRIMER CONSTRUCTOR BUILDER ------

    public static Builder builder(String name, Rol roles) {
        return new Builder(name, roles);
    }

    // ----------- CONSTRUCTORES SIN MÉTODO BUILDER ---------------------

    // public Person(String name, Rol roles) {
    // this.name = name;
    // this.birthDate = new Date();
    // this.roles = roles;
    // }

    // public Person(String name, Rol roles, Gender gender) {
    // this.name = name;
    // this.birthDate = new Date();
    // this.gender = gender;
    // this.roles = roles;
    // }

    // public Person(String name, String surname, LocalDate birthDate, Gender
    // gender) {
    // this.name = name;
    // this.surname = surname;
    // this.birthDate =
    // Date.from(birthDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    // this.gender = gender;
    // }

    // public Person(String name, String surname, String email, LocalDate birthDate,
    // Gender gender, Rol roles) {
    // this.name = name;
    // this.surname = surname;
    // this.email = email;
    // this.birthDate =
    // Date.from(birthDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    // this.gender = gender;
    // this.roles = roles;
    // }

    // public Person(String name, String surname, String email, LocalDate birthDate,
    // Gender gender, Rol roles,
    // Set<Projecto> projectos) {
    // this.name = name;
    // this.surname = surname;
    // this.email = email;
    // this.birthDate =
    // Date.from(birthDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    // this.gender = gender;
    // this.roles = roles;
    // this.projectos = projectos;
    // }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Rol getRoles() {
        return roles;
    }

    public void setRoles(Rol roles) {
        this.roles = roles;
    }

    public synchronized Set<Projecto> getProjectos() {
        return projectos;
    }

    public synchronized void setProjectos(Set<Projecto> projectos) {
        this.projectos = projectos;
    }

    @Override
    public String toString() {
        String strDateFormat = "hh:mm:ss dd-MM-yyyy"; // El formato de fecha está especificado
        SimpleDateFormat objSDF = new SimpleDateFormat(strDateFormat);
        return "{" + this.getId() + "}, " + gender + ", " + name + " " + surname + ", cumpleaños: "
                + objSDF.format(birthDate)
                + " " + email
                + " " + roles.getName();
        // + " " + this.getProjectos().toString(); // esto daría error al no cargar el
        // objeto
    }

    // ------------- CREAMOS PATRÓN BUILDER -----------------

    public static class Builder {

        private Person person;

        // Este constructor indica los parámetros principales
        private Builder(String name, Rol roles) {
            this.person = new Person();
            this.person.name = name;
            this.person.roles = roles;
        }

        // El resto de métodos son añadidos al Builder
        public Builder surname(String surname) {
            this.person.surname = surname;
            return this;
        }

        public Builder email(String email) {
            this.person.email = email;
            return this;
        }

        public Builder telefono(String telefono) {
            this.person.telefono = telefono;
            return this;
        }

        public Builder birthDate(LocalDate birthDate) {
            this.person.birthDate = Date.from(birthDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
            return this;
        }

        public Builder gender(Gender gender) {
            this.person.gender = gender;
            return this;
        }

        public Builder projecto(Set<Projecto> projectos) {
            this.person.projectos = projectos;
            return this;
        }

        public Person build() {
            return this.person;
        }

    }

}