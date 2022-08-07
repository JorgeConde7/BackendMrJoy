package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.Tarjeta;


public interface TarjetaRepository extends CrudRepository<Tarjeta, String>  {

	public Tarjeta findByNumTarjetaAndFechaVencimientoAndCodigoSeguridad (String numTarjeta, String fechaVencimiento, String codigoSeguridad);
}
