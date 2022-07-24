package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.BoletaEntrada;



public interface BoletaService {

	List<BoletaEntrada> listarBoleta() throws Exception;
	
	BoletaEntrada findById(Integer id) throws Exception;

	BoletaEntrada save(BoletaEntrada model) throws Exception;

	BoletaEntrada update(Integer id, BoletaEntrada model) throws Exception;

    boolean delete(Integer id) throws Exception;
}
