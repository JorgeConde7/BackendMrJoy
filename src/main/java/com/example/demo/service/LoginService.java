package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Login;

public interface LoginService {
	
	public Login validarUsuario(String usuario,String contrasenia, String tipouser);
	
	public Login create(Login login);
	
	public List<Login> findAll();
	
	public void delete(Long id);
	
	public Login listarporId(Long id);
	
	public String generateToken(Login userFound);

	Login buscarUsuario(String usuario, String password);
	
}
