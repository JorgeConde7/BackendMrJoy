package com.example.demo.service.impl;

import com.example.demo.model.Login;
import com.example.demo.repository.LoginRepository;
import com.example.demo.service.LoginService;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;


@Service
public class LoginServiceImpl implements LoginService {
	
	@Autowired
	private LoginRepository loginRepository;
	
	@Override
	public void crearLogin(Login login) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Login listarLoginUser(String usuario) {
		// TODO Auto-generated method stub
		return loginRepository.findByUsuario(usuario);
	}
}
