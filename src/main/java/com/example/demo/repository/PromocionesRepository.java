package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Promociones;

@Repository
public interface PromocionesRepository extends JpaRepository<Promociones,Long>{

}
