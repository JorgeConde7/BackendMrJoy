package com.example.demo.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Contacto;

@Repository
public interface ContactoRepository extends JpaRepository<Contacto,Long> {

	public List<Contacto> findByIdLogin(Long idLogin);
	
	public List<Contacto> findByCorreoAndFechaRegistroAndEstado(String correo,Date fecha,String estado);

	public List<Contacto> findByCorreo(String correo);
	public List<Contacto> findByFechaRegistro(Date fecha);
	public List<Contacto> findByEstado(String estado);
	

}
