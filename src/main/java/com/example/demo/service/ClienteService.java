package com.example.demo.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.demo.dto.ClienteDTO;
import com.example.demo.model.Cliente;

public interface ClienteService {
	
	public List<Cliente> findAll();
	public Cliente findById (Long Id);
	public Cliente save (Cliente cliente);
	public ResponseEntity<ClienteDTO> guardarDatos(ClienteDTO cliente) throws Exception;
	public Cliente findByIdLogin(Long idlogin);

}
