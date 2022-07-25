package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Login;

public interface LoginService {
	
	public Login listarLoginUser(String usuario,String contrasenia);
	
	public Login create(Login login);
	
	public List<Login> findAll();
	
	public void delete(Long id);
	
	public Login listarporId(Long id);
	
	
	
}
