package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.Cupon;

public interface CuponRepository extends JpaRepository<Cupon,Long> {

}
