package com.example.application.views.list;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.example.application.backend.data.entity.Person;
import com.example.application.backend.data.entity.PersonInfo;
import com.example.application.backend.data.entity.Projecto;
import com.example.application.backend.data.entity.Rol;
import com.example.application.backend.data.entity.Person.Gender;
import com.example.application.backend.data.service.implement.PersonServiceImplement;
import com.example.application.backend.data.service.implement.ProjectoServiceImplement;
import com.example.application.backend.data.service.implement.RolServiceImplement;

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

    @Autowired
    private ProjectoServiceImplement repoProjecto;

    @Test
    public void probando() {
        long inicio = System.currentTimeMillis();
        System.out.println("Hola Test");

        Rol roladmin = new Rol("ADMIN");
        Rol roluser = new Rol("USUARIO");

        roladmin = repoRol.save(roladmin); // grabo el objeto rol
        roluser = repoRol.save(roluser); // grabo el objeto user
        System.out.println("Grabo los objetos Rol");

        Projecto projecto1 = new Projecto("Proyecto1");
        Projecto projecto2 = new Projecto("Proyecto2");
        Projecto projecto3 = new Projecto("Proyecto3");
        System.out.println("Grabo los objetos Projecto");
        repoProjecto.save(projecto1);
        repoProjecto.save(projecto2);
        repoProjecto.save(projecto3);
        System.out.println("Grabo los objetos Projecto");

        Set<Projecto> proyectoList = new HashSet<>();
        proyectoList.add(projecto1); // añado solo 1 proyecto al Set

        repoPerson.save(Person.builder("Martín", roladmin)
                .surname("Director")
                .build());

        System.out.println("Grabo Persona tipo Admin");

        repoPerson.save(Person.builder("Oscar", roluser)
                .surname("Dominguez").email("emailoscar@ya.com")
                .birthDate(LocalDate.of(1990, 10, 10))
                .gender(Gender.HOMBRE)
                .projecto(proyectoList) // le añado proyecto
                .build());

        repoPerson.save(Person.builder("Dora", roluser)
                .surname("Exploradora").email("email2@ya.com")
                .birthDate(LocalDate.of(1976, 3, 5))
                .gender(Gender.MUJER)
                .build());

        repoPerson.save(Person.builder("Miriam", roluser)
                .surname("El Balcón").email("email3@ya.com")
                .birthDate(LocalDate.of(1996, 12, 21))
                .gender(Gender.MUJER)
                .build());

        repoPerson.save(Person.builder("Manuel", roluser)
                .surname("No Te Arrimes").email("email4@ya.com")
                .birthDate(LocalDate.of(1998, 9, 12))
                .gender(Gender.HOMBRE)
                .build());

        repoPerson.save(Person.builder("Noelia", roluser)
                .surname("De Zaracho").email("email5@ya.com")
                .birthDate(LocalDate.of(1984, 8, 14))
                .gender(Gender.MUJER)
                .build());

        repoPerson.save(Person.builder("Cristian", roluser)
                .surname("De Bizad").email("email6@ya.com")
                .birthDate(LocalDate.of(1988, 1, 17))
                .gender(Gender.HOMBRE)
                .build());

        proyectoList.clear(); // borro el List y añado 3 proyectos
        proyectoList.add(projecto1);
        proyectoList.add(projecto2);
        proyectoList.add(projecto3);

        repoPerson.save(Person.builder("Antonio", roluser)
                .surname("González").email("email7@ya.com")
                .birthDate(LocalDate.of(1973, 10, 16))
                .gender(Gender.HOMBRE)
                .projecto(proyectoList) // le añado proyectos
                .build());

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

        System.out.println("\nBorramos el campo Person que tiene el email emailoscar@ya.com");
        Person oPerson = new Person();
        oPerson = repoPerson.findByEmail("emailoscar@ya.com");
        repoPerson.deleteById(oPerson.getId());

        // fetch all persons
        System.out.println("\nfindAll() -- Mostramos todas las personas:");

        // Esta es la forma para una carga EAGER a través de una QUERY en donde si
        // mostramos los datos del proyecto
        for (Person p : repoPerson.findByPersonAndProjectos()) {
            System.out.println(p + " " + p.getProjectos().toString());
        }
        // Esta es la forma para una carga Lazy donde no podemos mostrar los datos del
        // Proyecto
        System.out.println("-------------------------");
        for (Person p : repoPerson.findAll()) {
            System.out.println(p);
        }

        System.out.println("-------------------------");

        System.out.println("Borramos el campo Person que tiene el email email2@ya.com");
        oPerson = repoPerson.findByEmail("email2@ya.com");
        repoPerson.deleteById(oPerson.getId());

        System.out.println("-------------------------");

        for (Person p : repoPerson.findAll()) {
            System.out.println(p);
        }

        System.out.println("-------------------------");

        System.out.println("Borramos el campo Person que tiene el email email6@ya.com");
        oPerson = repoPerson.findByEmail("email6@ya.com");
        repoPerson.deleteById(oPerson.getId());

        System.out.println("----------- MOSTRAMOS TODOS LOS PERSONS Y PROYECTOS --------------");

        // Esta es la forma para una carga EAGER a través de una QUERY en donde si
        // mostramos los datos del proyecto
        for (Person p : repoPerson.findByPersonAndProjectos()) {
            System.out.println(p + " " + p.getProjectos().toString());
        }

        System.out.println("----------- MOSTRAMOS PERSONS Y PROYECTOS --------------");

        Person person = repoPerson.findByEmail("email7@ya.com");
        System.out.println(person.toString() + " " + person.getProjectos().toString());

        long fin = System.currentTimeMillis();
        double tiempo = (double) ((fin - inicio) / 1000);
        System.out.println(tiempo + " segundos");

    }

}
