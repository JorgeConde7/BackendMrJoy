package com.example.demo.service;

import java.util.List;
import com.example.demo.dto.TotalVentasDTO;
import com.example.demo.model.Paquetes;

public interface PaqueteService {
	
	List<Paquetes> listarPaquetes() throws Exception;

	public List<TotalVentasDTO> totalPaquetes() throws Exception;
	
	Paquetes guardarPaquete(Paquetes model) throws Exception;

	Paquetes update(Integer id, Paquetes model) throws Exception;

	Paquetes buscarxId(Integer id) throws Exception;

    

}
