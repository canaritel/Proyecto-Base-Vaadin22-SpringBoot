package com.example.application.data.repository;

import java.util.Date;
import java.util.List;

import com.example.application.data.entity.Person;
import com.example.application.data.entity.PersonInfo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

    List<Person> findAllByBirthDateBetween(Date d1, Date d2);

    List<Person> findAllByGender(Person.Gender g);

    List<Person> findAllByBirthDateBetweenAndGender(Date d1, Date d2, Person.Gender gender);

    List<PersonInfo> findAllPersonInfoByGender(Person.Gender g);

    Person findByEmail(String email); // buscamos solo por email

    // see:
    // https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.query-creation

    @Query("Select p from Person p WHERE MONTH(p.birthDate) = :month")
    List<Person> findAllByBirthDateMonth(int month);

    @Query("Select p from Person p WHERE YEAR(p.birthDate) = :year")
    List<Person> findAllByBirthDateYear(int year);

    @Query("Select p.name from Person p")
    List<String> findAllNames();

    // Cuando tenemos que obtener varios datos de una consulta
    @Query("Select new com.example.application.data.entity.PersonInfo(p.id, p.name) from Person p")
    List<PersonInfo> findAllNamesAndIds();

    // En este caso hay una relación unidireccional ManyToMany entre Person y
    // Projecto. Para poder cargar los Proyectos que tiene cada Persona debemos
    // crear una Query. Al usar tipo de carga Lazy no tenemos los datos desde el
    // objeto Person y debemos crear este tipo de Query
    @Query("Select distinct p from Person p JOIN FETCH p.projectos")
    List<Person> findByPersonAndProjectos();

}
