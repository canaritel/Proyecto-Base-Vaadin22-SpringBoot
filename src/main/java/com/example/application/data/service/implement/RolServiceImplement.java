package com.example.application.data.service.implement;

import java.util.List;

import com.example.application.data.entity.Rol;
import com.example.application.data.repository.RolRepository;
import com.example.application.data.service.RolServiceInterfaz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RolServiceImplement implements RolServiceInterfaz {

    @Autowired
    private RolRepository rolRepository;

    @Override
    @Transactional(readOnly = true) // indicamos que es una operación de lectura
    public List<Rol> findAll() {
        return rolRepository.findAll();
    }

    @Override
    @Transactional // indicamos que es una operación escritura
    public Rol save(Rol rol) {
        return rolRepository.save(rol);
    }

    @Override
    @Transactional // indicamos que es una operación escritura
    public void deleteById(Integer id) {
        rolRepository.deleteById(id);
    }

}
