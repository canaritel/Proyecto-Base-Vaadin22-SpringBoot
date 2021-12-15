package com.example.application.data.service;

import java.util.List;

import com.example.application.data.entity.Rol;

public interface RolServiceInterfaz {

    public List<Rol> findAll();

    public Rol save(Rol rol);

    public void deleteById(Integer id);

}
