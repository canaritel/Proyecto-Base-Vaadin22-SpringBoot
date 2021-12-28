package com.example.application.backend.data.service.implement;

import java.util.List;

import com.example.application.backend.data.entity.Projecto;
import com.example.application.backend.data.repository.ProjectoRepository;
import com.example.application.backend.data.service.ProjectoServiceInterfaz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectoServiceImplement implements ProjectoServiceInterfaz {

    @Autowired
    private ProjectoRepository projectoRepository;

    @Override
    public List<Projecto> findAll() {
        return projectoRepository.findAll();
    }

    @Override
    public Projecto save(Projecto projecto) {
        return projectoRepository.save(projecto);
    }

    @Override
    public void deleteById(Integer id) {
        projectoRepository.deleteById(id);

    }

}
