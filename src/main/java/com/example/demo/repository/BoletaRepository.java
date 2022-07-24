package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.BoletaEntrada;

public interface BoletaRepository extends JpaRepository<BoletaEntrada, Integer> {

}
