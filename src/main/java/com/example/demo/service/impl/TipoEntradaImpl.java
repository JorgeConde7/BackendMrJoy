package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.TotalVentasDTO;
import com.example.demo.model.Reserva;
import com.example.demo.model.TipoEntrada;
import com.example.demo.repository.TipoEntradaRepository;
import com.example.demo.service.TipoEntradaService;


@Service
public class TipoEntradaImpl implements TipoEntradaService {
	
	@Autowired
	private TipoEntradaRepository tipoentradarepository;

	@Override
	@Transactional(readOnly=true)
	public List<TipoEntrada> findAll() {
		// TODO Auto-generated method stub
		return (List<TipoEntrada>) tipoentradarepository.findAll();
	}

	@Override
	@Transactional
	public TipoEntrada findById(Long id) {
		// TODO Auto-generated method stub
		return tipoentradarepository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public TipoEntrada save(TipoEntrada tipoentrada) {
		
		return tipoentradarepository.save(tipoentrada);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		tipoentradarepository.deleteById(id);
		
	}

	@Override
	public List<TotalVentasDTO> totalEntradas() throws Exception{
		return null;
	}
	
}
