package com.example.demo.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.demo.model.Cliente;
import com.example.demo.model.Login;
import com.example.demo.repository.ClienteRepository;
import com.example.demo.repository.LoginRepository;
import com.example.demo.service.LoginService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import com.example.demo.util.Constantes;

@Service
public class LoginServiceImpl implements LoginService {
	
	@Value("${JWT_SECRET_TOKEN}")
	private String JWT_SECRET_TOKEN;

	@Autowired
	private LoginRepository loginRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;

	@Override
	@Transactional(readOnly=true)
	public Login buscarUsuario(String usuario,String password) {
		return loginRepository.findByUsuarioAndContrasenia(usuario, password);
	}
	
	@Override
	@Transactional(readOnly=true)
	public Login validarUsuario(String usuario,String password,String tipouser) {
		
		return loginRepository.findByUsuarioAndContraseniaAndTipouser(usuario,password,tipouser);
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
		
		String nombres,apellidos,dni,telefono,correo;
		Algorithm algorithm = Algorithm.HMAC256(JWT_SECRET_TOKEN);
		
		String username = userFound.getUsuario();
		String profile = userFound.getTipouser();
		Long idLogin = userFound.getIdLogin();
		
		Cliente cliente= new Cliente();
		cliente= clienteRepository.findByIdLogin(idLogin);
		
		nombres=cliente.getNombres();
		apellidos=cliente.getApePaterno()+" "+cliente.getApeMaterno();
		dni= cliente.getDni();
		telefono=cliente.getTelefono();
		correo=cliente.getCorreo();
		

		String token = JWT.create()
				.withClaim("username", username)
				.withClaim("profile", profile)
				.withClaim("nombres", nombres)
				.withClaim("apellidos", apellidos)
				.withClaim("dni", dni)
				.withClaim("telefono", telefono)
				.withClaim("correo", correo)		
				.withClaim("id", idLogin)
				.withClaim("create_at", new Date())
				.sign(algorithm);
						
		return token;
	}


		
	
}
