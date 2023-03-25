package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Login;

public interface LoginService {
	
	public Login validarUsuario(String usuario,String contrasenia, String tipouser);
	
	public Login crearUsuario(Login login) throws Exception;

	
	public Login listarporId(Long id);
	
	public String generateToken(Login userFound);

	
}
