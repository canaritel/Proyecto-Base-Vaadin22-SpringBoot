package com.example.application.backend.data.service.implement;

import java.util.Date;
import java.util.List;

import com.example.application.backend.data.entity.Person;
import com.example.application.backend.data.entity.PersonInfo;
import com.example.application.backend.data.entity.Person.Gender;
import com.example.application.backend.data.repository.PersonRepository;
import com.example.application.backend.data.service.PersonServiceInterfaz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PersonServiceImplement implements PersonServiceInterfaz {

    @Autowired
    private PersonRepository personRepository;

    @Override
    @Transactional(readOnly = true) // más información sobre transactional:
                                    // https://programmerclick.com/article/41201877287/
    public List<Person> findAll() {
        return personRepository.findAll();
    }

    @Override
    @Transactional
    public Person save(Person persona) {
        return personRepository.save(persona);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        personRepository.deleteById(id);
    }

    /* **** Creamos distintos métodos para List *** */

    @Override
    @Transactional(readOnly = true)
    public List<Person> findAllByBirthDateBetween(Date d1, Date d2) {
        return personRepository.findAllByBirthDateBetween(d1, d2);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Person> findAllByGender(Gender g) {
        return personRepository.findAllByGender(g);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Person> findAllByBirthDateBetweenAndGender(Date d1, Date d2, Gender gender) {
        return personRepository.findAllByBirthDateBetweenAndGender(d1, d2, gender);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Person> findAllByBirthDateMonth(int month) {
        return personRepository.findAllByBirthDateMonth(month);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Person> findAllByBirthDateYear(int year) {
        return personRepository.findAllByBirthDateYear(year);
    }

    @Override
    @Transactional(readOnly = true)
    public List<String> findAllNames() {
        return personRepository.findAllNames();
    }

    @Override
    @Transactional(readOnly = true)
    public List<PersonInfo> findAllNamesAndIds() {
        return personRepository.findAllNamesAndIds();
    }

    @Override
    @Transactional(readOnly = true)
    public List<PersonInfo> findAllPersonInfoByGender(Gender g) {
        return personRepository.findAllPersonInfoByGender(g);
    }

    @Override
    @Transactional(readOnly = true)
    public Person findByEmail(String email) {
        // Método para poder traernos el objeto Proyecto asociado al ManyToManu con
        // carga perezosa Lazy
        Person oPerson = personRepository.findByEmail(email);
        oPerson.getProjectos().size(); // esto fuerza a traernos todo el Proyecto
        return oPerson;
    }

    @Override
    public List<Person> findByPersonAndProjectos() {
        return personRepository.findByPersonAndProjectos();
    }

}
