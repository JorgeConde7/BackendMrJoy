package com.example.demo.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Cliente;
import com.example.demo.model.Reserva;

@Repository
public interface ReservaRepository extends CrudRepository<Reserva, Long>
{

	List<Reserva> findByfechaReserva(Date fechaReserva);
	List<Reserva> findByIdLogin(int idLogin);

}
