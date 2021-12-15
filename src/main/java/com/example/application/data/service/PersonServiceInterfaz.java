package com.example.application.data.service;

import java.util.Date;
import java.util.List;

import com.example.application.data.entity.Person;
import com.example.application.data.entity.PersonInfo;

public interface PersonServiceInterfaz {

    public List<Person> findAll();

    public Person save(Person Ficha);

    public void deleteById(Integer id);

    public Person findByEmail(String email);

    public List<Person> findAllByBirthDateBetween(Date d1, Date d2);

    public List<Person> findAllByGender(Person.Gender g);

    public List<Person> findAllByBirthDateBetweenAndGender(Date d1, Date d2, Person.Gender gender);

    public List<Person> findAllByBirthDateMonth(int month);

    public List<Person> findAllByBirthDateYear(int year);

    public List<String> findAllNames();

    public List<PersonInfo> findAllNamesAndIds();

    public List<PersonInfo> findAllPersonInfoByGender(Person.Gender g);

}
