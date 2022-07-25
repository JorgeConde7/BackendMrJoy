package com.example.demo.service.impl;

import com.example.demo.model.Login;
import com.example.demo.repository.LoginRepository;
import com.example.demo.service.LoginService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;


@Service
public class LoginServiceImpl implements LoginService {
	
	@Autowired
	
	private LoginRepository loginRepository;

	@Override
	@Transactional(readOnly=true)
	public Login listarLoginUser(String usuario,String password) {
		// TODO Auto-generated method stub
		return loginRepository.findByUsuarioAndContrasenia(usuario,password);
	}

	@Override
	@Transactional
	public Login create(Login login) {
		return loginRepository.save(login);
		
	}

	@Override
	public List<Login> findAll() {
		// TODO Auto-generated method stub
		return loginRepository.findAll();
	}
}
