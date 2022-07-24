package com.example.demo.service;

import java.sql.Date;
import java.util.List;

import com.example.demo.model.Reserva;

public interface ReservaService 
{
	public List<Reserva> findAll();
	public Reserva findById(Long id);
	public Reserva save(Reserva reserva);
	public void delete(Long id);
	
	public List<Reserva> ListarPorFecha(Date fechaReserva);
	
}
