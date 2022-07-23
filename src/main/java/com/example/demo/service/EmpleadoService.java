package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Empleado;

public interface EmpleadoService {
	public List<Empleado> findAll();
	public Empleado findById(Long id);
	public Empleado save (Empleado empleado);
	public void delete (Long id);
}
