package com.example.application.data.service.implement;

import java.util.List;

import com.example.application.data.entity.Contact;
import com.example.application.data.repository.ContactRepository;
import com.example.application.data.service.ContactServiceInterfaz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ContactServiceImplement implements ContactServiceInterfaz {

    @Autowired
    private ContactRepository contactRepository;

    @Override
    @Transactional(readOnly = true) // más información sobre transactional:
                                    // https://programmerclick.com/article/41201877287/
    public List<Contact> findAll() {
        return contactRepository.findAll();
    }

    @Override
    @Transactional
    public Contact save(Contact contact) {
        return contactRepository.save(contact);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        contactRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Contact> findAllContacts(String stringFilter) {
        if (stringFilter == null || stringFilter.isEmpty()) {
            return contactRepository.findAll();
        } else {
            return contactRepository.search(stringFilter);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public long countContacts() {
        return contactRepository.count();
    }

}
