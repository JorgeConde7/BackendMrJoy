package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.Cliente;

@Service
public interface ClienteService {

	List<Cliente> listarCliente() throws Exception;
	
    Cliente findById(Integer id) throws Exception;

    Cliente save(Cliente model) throws Exception;

    Cliente update(Integer id, Cliente model) throws Exception;

    boolean delete(Integer id) throws Exception;

}
