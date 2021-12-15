package com.example.application.data.service.implement;

import java.util.List;

import com.example.application.data.entity.Status;
import com.example.application.data.repository.StatusRepository;
import com.example.application.data.service.StatusServiceInterfaz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StatusServiceImplement implements StatusServiceInterfaz {

    @Autowired
    private StatusRepository statusRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Status> findAll() {
        return statusRepository.findAll();
    }

    @Override
    @Transactional
    public Status save(Status status) {
        return statusRepository.save(status);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        statusRepository.deleteById(id);
    }

}
