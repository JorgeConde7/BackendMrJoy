package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Contacto;

@Repository
public interface ContactoRepository extends JpaRepository<Contacto,Long> {

	public List<Contacto> findByIdLogin(Long idLogin);
}
