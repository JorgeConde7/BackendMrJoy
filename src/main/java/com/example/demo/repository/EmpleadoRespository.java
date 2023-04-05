package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Empleado;

@Repository
public interface EmpleadoRespository extends JpaRepository<Empleado,Long>{

	public Empleado findByIdLogin(Long idLogin);
	boolean  existsByCorreo(String correo);
}
