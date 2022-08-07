package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Tarjeta;


public interface TarjetaService 
{
	public List<Tarjeta> findAll ();
	public Tarjeta findById(String numTarjeta);
	public Tarjeta save (Tarjeta tarjeta);
	public void delete (String numTarjeta);
	public Tarjeta saldo(String numTarjeta, String fechaVencimiento, String codigoSeguridad);
}
