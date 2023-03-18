package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Parque;

public interface ParquesService {

	List<Parque> listarParques() throws Exception;
	
	Parque guardarParque(Parque model) throws Exception;

	Parque actualizarParque(Integer id, Parque model) throws Exception;

	void eliminarParque(Integer id) throws Exception;


}
