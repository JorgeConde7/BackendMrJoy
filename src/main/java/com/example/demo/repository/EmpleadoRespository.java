package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.Empleado;

public interface EmpleadoRespository extends CrudRepository<Empleado,Long>{

}
