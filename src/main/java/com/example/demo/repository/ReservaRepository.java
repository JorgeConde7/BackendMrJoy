package com.example.demo.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.Cliente;
import com.example.demo.model.Reserva;

public interface ReservaRepository extends CrudRepository<Reserva, Long>
{

	List<Reserva> findByfechaReserva(Date fechaReserva);
	List<Reserva> findByIdLogin(int idLogin);

}
