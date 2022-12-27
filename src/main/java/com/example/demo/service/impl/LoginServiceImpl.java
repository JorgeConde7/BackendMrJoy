package com.example.demo.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.demo.model.Login;
import com.example.demo.repository.LoginRepository;
import com.example.demo.service.LoginService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;


@Service
public class LoginServiceImpl implements LoginService {
	
	@Value("${JWT_SECRET_TOKEN}")
	private String JWT_SECRET_TOKEN;

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
	@Transactional(readOnly = true)
	public List<Login> findAll() {
		// TODO Auto-generated method stub
		return loginRepository.findAll();
	}

	@Override
	@Transactional
	public void delete(Long id) {
		loginRepository.deleteById(id);
		
	}

	@Override
	public Login listarporId(Long id) {
		// TODO Auto-generated method stub
		return loginRepository.findById(id).orElse(null);
	}

	@Override
	public String generateToken(Login userFound) {
		Algorithm algorithm = Algorithm.HMAC256(JWT_SECRET_TOKEN);
		
		String username = userFound.getUsuario();
		String profile = userFound.getTipouser();
		Long idLogin = userFound.getIdLogin();
		
		String token = JWT.create()
				.withClaim("username", username)
				.withClaim("profile", profile)
				.withClaim("id", idLogin)
				.withClaim("create_at", new Date())
				.sign(algorithm);
						
		return token;
	}


		
	
}
