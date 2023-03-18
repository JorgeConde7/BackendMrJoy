package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Contacto;

@Repository
public interface ContactoRepository extends CrudRepository <Contacto, Long>{

}
