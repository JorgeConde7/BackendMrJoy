package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.ClienteDTO;
import com.example.demo.exception.MrJoyException;
//import com.example.demo.dto.EmpleadoDTO;
import com.example.demo.model.Cliente;
import com.example.demo.model.Empleado;
import com.example.demo.model.Login;
import com.example.demo.repository.ClienteRepository;
import com.example.demo.repository.LoginRepository;
import com.example.demo.service.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService{

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private LoginRepository loginRepository;
	
	@Override
	public List<Cliente> findAll() {
		return (List<Cliente>) clienteRepository.findAll();
	}

	@Override
	@Transactional
	public Cliente findById(Long Id) {
		return clienteRepository.findById(Id).orElse(null);
	}

	@Override
	@Transactional
	public Cliente save(Cliente cliente) {
		return clienteRepository.save(cliente);
	}

	
	@Override
	public ResponseEntity<ClienteDTO> guardarDatos(ClienteDTO clienteDTO) throws Exception {
		
		if (loginRepository.existsByUsuario(clienteDTO.getUsuario())) {
		    throw new MrJoyException("COD05","Ya existe un usuario, intente con otro");
		}
		
		if (clienteRepository.existsByCorreo(clienteDTO.getCorreo())) {
		    throw new MrJoyException("COD05","Ya existe un correo asociado, intente con otro");
		}
		
		/*
		List<Login> listalogin = loginRepository.findAll();
		for (Login login : listalogin) {

			if (clienteDTO.getUsuario().equals(login.getUsuario())) {
				throw new MrJoyException("COD05","Ya existe un usuario, intente con otro");
			}
		}
		
		List<Cliente> listaCliente = (List<Cliente>) clienteRepository.findAll();
		for (Cliente cliente : listaCliente) {

			if (clienteDTO.getCorreo().equals(cliente.getCorreo())) {
				throw new MrJoyException("COD05","Ya existe un correo asociado, intente con otro");
			}
		}
		*/
		
		Login loginDato = new Login();
		loginDato.setUsuario(clienteDTO.getUsuario());
		loginDato.setContrasenia(clienteDTO.getContrasenia());
		loginDato.setTipouser(clienteDTO.getTipouser());
		
		Login datoLogin=loginRepository.save(loginDato); 
		
		Cliente clienteDato = new Cliente();
		clienteDato.setApePaterno(clienteDTO.getApePaterno());
		clienteDato.setApeMaterno(clienteDTO.getApeMaterno());
		clienteDato.setDni(clienteDTO.getDni());
		clienteDato.setNombres(clienteDTO.getNombres());
		clienteDato.setCorreo(clienteDTO.getCorreo());
		clienteDato.setTelefono(clienteDTO.getTelefono());
		clienteDato.setDireccion(clienteDTO.getDireccion());
		clienteDato.setGenero(clienteDTO.getGenero());
		clienteDato.setIdLogin(datoLogin.getIdLogin());
		clienteDato.setFechaNacimiento(clienteDTO.getFechaNacimiento());
		
		clienteRepository.save(clienteDato);
				
		return ResponseEntity.status(HttpStatus.OK).body(clienteDTO);
	}

	@Override
	@Transactional
	public Cliente findByIdLogin(Long idlogin) {
		return clienteRepository.findByIdLogin(idlogin);
	}


}
