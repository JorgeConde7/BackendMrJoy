package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Tarjeta;
import com.example.demo.repository.TarjetaRepository;
import com.example.demo.service.TarjetaService;

@Service
public class TarjetaServiceImpl implements TarjetaService{

	@Autowired
	private TarjetaRepository tarjetaRepository;
	
	@Override
	@Transactional(readOnly=true)
	public List<Tarjeta> findAll() {
		return (List<Tarjeta>) tarjetaRepository.findAll();
	}

	@Override
	@Transactional
	public Tarjeta findById(String numTarjeta) {
		return tarjetaRepository.findById(numTarjeta).orElse(null);
	}

	@Override
	@Transactional
	public Tarjeta save(Tarjeta tarjeta) {
		return tarjetaRepository.save(tarjeta);
	}

	@Override
	@Transactional
	public void delete(String numTarjeta) {
		tarjetaRepository.deleteById(numTarjeta);
	}

	@Override
	public Tarjeta saldo(String numTarjeta, String fechaVencimiento, String codigoSeguridad) 
	{
		return tarjetaRepository.findByNumTarjetaAndFechaVencimientoAndCodigoSeguridad(numTarjeta, fechaVencimiento, codigoSeguridad);
	}


}
