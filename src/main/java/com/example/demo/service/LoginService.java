package com.example.demo.service;

import com.example.demo.model.Login;

public interface LoginService {
	
	public Login listarLoginUser(String usuario);
	
	public void crearLogin(Login login);
	
}
