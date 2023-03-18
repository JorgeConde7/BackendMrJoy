package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Tarjeta;

@Repository
public interface TarjetaRepository extends CrudRepository<Tarjeta, String>  {

	public Tarjeta findByNumTarjetaAndFechaVencimientoAndCodigoSeguridad (String numTarjeta, String fechaVencimiento, String codigoSeguridad);
}
