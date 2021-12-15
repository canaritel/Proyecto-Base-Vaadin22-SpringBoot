package com.example.application.data.service;

import java.util.List;

import com.example.application.data.entity.Contact;

public interface ContactServiceInterfaz {

    public List<Contact> findAll();

    public Contact save(Contact contact);

    public void deleteById(Integer id);

    public List<Contact> findAllContacts(String stringFilter);

    public long countContacts();

}
