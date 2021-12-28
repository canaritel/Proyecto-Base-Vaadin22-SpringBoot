package com.example.application.backend.data.repository;

import com.example.application.backend.data.entity.Projecto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectoRepository extends JpaRepository<Projecto, Integer> {

}
