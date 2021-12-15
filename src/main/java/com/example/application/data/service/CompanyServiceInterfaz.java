package com.example.application.data.service;

import java.util.List;

import com.example.application.data.entity.Company;

public interface CompanyServiceInterfaz {

    public List<Company> findAll();

    public Company save(Company company);

    public void deleteById(Integer id);

}
