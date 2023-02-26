package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.Cliente;

public interface ClienteRepository extends CrudRepository<Cliente, Long>{
	
	public Cliente findByIdLogin(Long idLogin);

}
