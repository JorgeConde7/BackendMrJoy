package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Cliente;

public interface ClienteService {
	
	public List<Cliente> findAll();
	public Cliente findById (Long Id);
	public Cliente save (Cliente cliente);
	public void delete (Long Id);
	

}
