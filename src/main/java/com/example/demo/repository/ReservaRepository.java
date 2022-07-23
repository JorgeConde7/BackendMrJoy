package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.Reserva;

public interface ReservaRepository extends CrudRepository<Reserva, Long>
{

}
