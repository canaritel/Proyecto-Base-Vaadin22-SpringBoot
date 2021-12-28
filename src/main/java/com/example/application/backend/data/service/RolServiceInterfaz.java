package com.example.application.backend.data.service;

import java.util.List;

import com.example.application.backend.data.entity.Rol;

public interface RolServiceInterfaz {

    public List<Rol> findAll();

    public Rol save(Rol rol);

    public void deleteById(Integer id);

}
