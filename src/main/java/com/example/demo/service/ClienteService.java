package com.example.demo.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.demo.dto.ClienteDTO;
import com.example.demo.model.Cliente;

public interface ClienteService {
	
	public List<Cliente> findAll();
	public Cliente findById (Long Id);
	public Cliente save (Cliente cliente);
	public void delete (Long Id);
	
	public ResponseEntity<ClienteDTO> guardarDatos(ClienteDTO cliente);
	
	public Cliente findByIdLogin(Long idlogin);

}
