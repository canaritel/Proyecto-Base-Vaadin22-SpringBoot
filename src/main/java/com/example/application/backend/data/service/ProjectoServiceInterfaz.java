package com.example.application.backend.data.service;

import java.util.List;

import com.example.application.backend.data.entity.Projecto;

public interface ProjectoServiceInterfaz {

    public List<Projecto> findAll();

    public Projecto save(Projecto projecto);

    public void deleteById(Integer id);

}
