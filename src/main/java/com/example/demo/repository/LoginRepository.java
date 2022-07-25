package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Login;
import java.util.List;

@Repository

public interface LoginRepository extends JpaRepository<Login, Long>{
	public Login findByUsuario (String usuario);
	
	public Login findByUsuarioAndContrasenia (String usuario, String password);
	
	public Login save (Login login);
	
	public List<Login> findAll();
}
