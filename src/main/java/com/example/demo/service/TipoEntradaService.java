package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.TotalVentasDTO;
import com.example.demo.model.TipoEntrada;


public interface TipoEntradaService {
	
	public List<TipoEntrada> findAll ();
	public TipoEntrada findById(Long id);
	public TipoEntrada save (TipoEntrada tipoentrada);
	public void delete (Long id);
	public List<TotalVentasDTO> totalEntradas() throws Exception;

}
