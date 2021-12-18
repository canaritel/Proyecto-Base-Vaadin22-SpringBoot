package com.example.application.data.service.implement;

import java.util.Date;
import java.util.List;

import com.example.application.data.entity.Person;
import com.example.application.data.entity.Person.Gender;
import com.example.application.data.entity.PersonInfo;
import com.example.application.data.repository.PersonRepository;
import com.example.application.data.service.PersonServiceInterfaz;

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
        // Al ser Projecto un List cargado en modo Lazy debemos traernos el objeto
        // Cuando sea un ManyToMany o un OneToMany
        // Person oUser = personRepository.findByEmail(email);
        // oUser.getEmail().size(); // este método obliga a traerse a todo el objeto
        return personRepository.findByEmail(email); // ya podemos imprimir Projecto
    }

    @Override
    public List<Person> findByPersonAndProjectos() {
        return personRepository.findByPersonAndProjectos();
    }

}
