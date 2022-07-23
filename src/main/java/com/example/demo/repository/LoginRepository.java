package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.Login;



public interface LoginRepository extends JpaRepository<Login, Long>{
	public Login findByUsuario (String usuario);
}
