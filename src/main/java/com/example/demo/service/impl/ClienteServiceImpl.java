package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.ClienteDTO;
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
	private ClienteRepository clienteDao;
	
	@Autowired
	private LoginRepository loginRepository;
	
	@Override
	public List<Cliente> findAll() {
		return (List<Cliente>) clienteDao.findAll();
	}

	@Override
	@Transactional
	public Cliente findById(Long Id) {
		return clienteDao.findById(Id).orElse(null);
	}

	@Override
	@Transactional
	public Cliente save(Cliente cliente) {
		return clienteDao.save(cliente);
	}

	@Override
	@Transactional
	public void delete(Long Id) {
		clienteDao.deleteById(Id);

	}
	
	@Override
	public ResponseEntity<ClienteDTO> guardarDatos(ClienteDTO clienteDTO) {
		

		Login login = new Login();
		login.setUsuario(clienteDTO.getUsuario());
		login.setContrasenia(clienteDTO.getContrasenia());
		login.setTipouser(clienteDTO.getTipouser());
		
		Login datoLogin=loginRepository.save(login); 
		
		Cliente clienteDato = new Cliente();
		clienteDato.setApePaterno(clienteDTO.getApePaterno());
		clienteDato.setApeMaterno(clienteDTO.getApeMaterno());
		clienteDato.setDni(clienteDTO.getDni());
		clienteDato.setNombres(clienteDTO.getNombres());
		clienteDato.setCorreo(clienteDTO.getCorreo());
		clienteDato.setTelefono(clienteDTO.getTelefono());
		clienteDato.setDireccion(clienteDTO.getDireccion());
		clienteDato.setGenero(clienteDTO.getGenero());
		clienteDato.setIdLogin(datoLogin.getIdLogin().intValue());
		clienteDato.setFechaNacimiento(clienteDTO.getFechaNacimiento());
				
		Cliente clienteoGuardado=clienteDao.save(clienteDato);
		
				
		return ResponseEntity.status(HttpStatus.OK).body(clienteDTO);
	}

}
