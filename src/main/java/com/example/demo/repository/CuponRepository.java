package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Cupon;

@Repository
public interface CuponRepository extends JpaRepository<Cupon,Long> {

}
