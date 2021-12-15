package com.example.application.data.service;

import java.util.List;

import com.example.application.data.entity.Status;

public interface StatusServiceInterfaz {

    public List<Status> findAll();

    public Status save(Status status);

    public void deleteById(Integer id);

}
