package com.example.demo.service;

import java.sql.Date;
import java.util.List;

import com.example.demo.dto.DataResponseDTO;
import com.example.demo.model.Reserva;

public interface ReservaService 
{
	public List<Reserva> findAll();
	public Reserva findById(Long id);
	public Reserva guardarReserva(Reserva reserva) throws Exception;
	public DataResponseDTO<String> eliminarReserva(Reserva reserva, Long id) throws Exception;
	
	public List<Reserva> ListarPorFecha(Date fechaReserva);
	public List<Reserva> listarPorIdLogin(int idLogin);
	
	public List<Reserva> buscarReserva(String campo,String valor)throws Exception;;
}
