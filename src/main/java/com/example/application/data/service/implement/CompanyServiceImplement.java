package com.example.application.data.service.implement;

import java.util.List;

import com.example.application.data.entity.Company;
import com.example.application.data.repository.CompanyRepository;
import com.example.application.data.service.CompanyServiceInterfaz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CompanyServiceImplement implements CompanyServiceInterfaz {

    @Autowired
    private CompanyRepository companyRepository;

    @Override
    @Transactional(readOnly = true) // más información sobre transactional:
                                    // https://programmerclick.com/article/41201877287/
    public List<Company> findAll() {
        return companyRepository.findAll();
    }

    @Override
    @Transactional
    public Company save(Company company) {
        return companyRepository.save(company);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        companyRepository.deleteById(id);
    }

}
