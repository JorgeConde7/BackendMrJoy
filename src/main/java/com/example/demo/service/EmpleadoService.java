package com.example.demo.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.demo.dto.EmpleadoDTO;
import com.example.demo.model.Empleado;

public interface EmpleadoService {
	public List<Empleado> findAll();
	public Empleado findById(Long id);
	public Empleado save (Empleado empleado);
	public void delete (Long id);
	
	public ResponseEntity<EmpleadoDTO> guardarDatos(EmpleadoDTO empleado);
}
