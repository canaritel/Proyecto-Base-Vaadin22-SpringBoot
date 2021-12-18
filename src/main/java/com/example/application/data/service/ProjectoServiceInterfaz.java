package com.example.application.data.service;

import java.util.List;

import com.example.application.data.entity.Projecto;

public interface ProjectoServiceInterfaz {

    public List<Projecto> findAll();

    public Projecto save(Projecto projecto);

    public void deleteById(Integer id);

}
