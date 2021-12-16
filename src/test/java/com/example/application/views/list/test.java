package com.example.application.views.list;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import com.example.application.data.entity.Person;
import com.example.application.data.entity.PersonInfo;
import com.example.application.data.entity.Person.Gender;
import com.example.application.data.entity.Rol;
import com.example.application.data.service.implement.PersonServiceImplement;
import com.example.application.data.service.implement.RolServiceImplement;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class test {

    @Autowired
    private PersonServiceImplement repoPerson;

    @Autowired
    private RolServiceImplement repoRol;

    @Test
    public void probando() {
        System.out.println("Hola Test");

        Rol roladmin = new Rol("ADMIN");
        Rol roluser = new Rol("USUARIO");

        roladmin = repoRol.save(roladmin); // grabo el objeto rol
        roluser = repoRol.save(roluser); // grabo el objeto user
        System.out.println("Grabo los objetos Rol");

        repoPerson.save(
                new Person("Oscar", "Dominguez", "emailoscar@ya.com", LocalDate.of(1980, 10, 10), Person.Gender.HOMBRE,
                        roluser));
        System.out.println("Grabo Persona");

        repoPerson.save(new Person("Dora", "Exploradora", "email2@ya.com", LocalDate.of(1976, 3, 5), Gender.MUJER,
                roluser));
        repoPerson.save(
                new Person("Miriam", "El Balcón", "email3@ya.com", LocalDate.of(1996, 12, 21), Gender.MUJER, roluser));
        repoPerson.save(new Person("Manuel", "Notearrimes", "email4@ya.com", LocalDate.of(1998, 9, 12),
                Gender.HOMBRE, roluser));
        repoPerson.save(new Person("Noelia", "Zarazgo", "email5@ya.com", LocalDate.of(1984, 8, 14), Gender.MUJER,
                roluser));
        repoPerson.save(new Person("Cristial", "Miguel", "email6@ya.com", LocalDate.of(1988, 1, 17), Gender.HOMBRE,
                roluser));
        repoPerson.save(new Person("Pepe", "Pepito", "email7@ya.com", LocalDate.of(1973, 10, 16), Gender.HOMBRE,
                roladmin));
        // fetch all persons
        System.out.println("\nfindAll() -- Mostrar todos:");
        for (Person p : repoPerson.findAll()) {
            System.out.println(p);
        }

        System.out.println("\nfindAll -- Nacidos entre 1980 y 2000:");
        for (Person p : repoPerson.findAllByBirthDateBetween(
                Date.from(LocalDate.of(1980, 1, 1).atStartOfDay(ZoneId.systemDefault()).toInstant()),
                Date.from(LocalDate.of(2000, 12, 31).atStartOfDay(ZoneId.systemDefault()).toInstant()))) {
            System.out.println(p);
        }

        System.out.println("\nfindAll -- Nacidos en Diciembre:");
        for (Person p : repoPerson.findAllByBirthDateMonth(12)) {
            System.out.println(p);
        }

        System.out.println("\nfindAll -- Nacidos en 1998:");
        for (Person p : repoPerson.findAllByBirthDateYear(1998)) {
            System.out.println(p);
        }

        System.out.println("\nfindAll -- Mujeres:");
        for (Person p : repoPerson.findAllByGender(Gender.MUJER)) {
            System.out.println(p);
        }

        System.out.println("\nfindAll -- Mujeres nacidas entre 1980-2000:");
        for (Person p : repoPerson.findAllByBirthDateBetweenAndGender(
                Date.from(LocalDate.of(1980, 1, 1).atStartOfDay(ZoneId.systemDefault()).toInstant()),
                Date.from(LocalDate.of(2000, 12, 31).atStartOfDay(ZoneId.systemDefault()).toInstant()),
                Gender.MUJER)) {
            System.out.println(p);
        }

        System.out.println("\nfindAll -- Nombres:");
        for (String name : repoPerson.findAllNames()) {
            System.out.println(name);
        }

        System.out.println("\nfindAll -- Nombres e Ids:");
        for (PersonInfo p : repoPerson.findAllNamesAndIds()) {
            System.out.println(p);
        }

        System.out.println("\nfindAll -- Nombres e Ids por Sexo: Hombre");
        for (PersonInfo p : repoPerson.findAllPersonInfoByGender(Gender.HOMBRE)) {
            System.out.println(p);
        }

        System.out.println("\nfindAll() -- Mostrar Roles:");
        for (Rol r : repoRol.findAll()) {
            System.out.println(r);
        }

        System.out.println("Borramos el campo Person que tiene el email emailoscar@ya.com");
        Person oPerson = new Person();
        oPerson = repoPerson.findByEmail("emailoscar@ya.com");
        repoPerson.deleteById(oPerson.getId());

        // fetch all persons
        System.out.println("\nfindAll() -- Mostramos todas las personas:");
        for (Person p : repoPerson.findAll()) {
            System.out.println(p);
        }

    }

}
