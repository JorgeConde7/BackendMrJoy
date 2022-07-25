package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Paquetes;

public interface PaqueteRepository extends JpaRepository<Paquetes, Integer> {

}
