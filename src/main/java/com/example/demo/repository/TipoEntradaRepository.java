package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.TipoEntrada;

@Repository
public interface TipoEntradaRepository extends CrudRepository<TipoEntrada,Long> {
	

}
