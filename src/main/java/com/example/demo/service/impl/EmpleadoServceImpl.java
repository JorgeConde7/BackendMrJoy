package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.EmpleadoDTO;
import com.example.demo.model.Empleado;
import com.example.demo.model.Login;
import com.example.demo.repository.EmpleadoRespository;
import com.example.demo.repository.LoginRepository;
import com.example.demo.service.EmpleadoService;

@Service
public class EmpleadoServceImpl implements EmpleadoService {
	
	@Autowired
	private EmpleadoRespository empleadoRespository;
	
	@Autowired
	private LoginRepository loginRepository;
	
	@Override
	@Transactional(readOnly=true)
	public List<Empleado> findAll() {
		// TODO Auto-generated method stub
		return (List<Empleado>)empleadoRespository.findAll();
	}
	@Transactional
	@Override
	public Empleado findById(Long id) {
		// TODO Auto-generated method stub
		return empleadoRespository.findById(id).orElse(null);
	}
	@Transactional
	@Override
	public Empleado save(Empleado empleado) {
		// TODO Auto-generated method stub
		return empleadoRespository.save(empleado);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		// TODO Auto-generated method stub
		empleadoRespository.deleteById(id);
	}
	

	@Override
	public ResponseEntity<EmpleadoDTO> guardarDatos(EmpleadoDTO empleadoDTO) {
		
		Login login = new Login();
		login.setUsuario(empleadoDTO.getUsuario());
		login.setContrasenia(empleadoDTO.getContrasenia());
		login.setTipouser(empleadoDTO.getTipouser());
		
		Login DatoLogin=loginRepository.save(login); 
		
		Empleado empleadoDato = new Empleado();
		empleadoDato.setApellidos(empleadoDTO.getApellidos());
		empleadoDato.setNombres(empleadoDTO.getNombres());
		empleadoDato.setCorreo(empleadoDTO.getCorreo());
		empleadoDato.setFechaNacimiento(empleadoDTO.getFechaNacimiento());
		empleadoDato.setTelefono(empleadoDTO.getTelefono());
		empleadoDato.setTurno(empleadoDTO.getTurno());
		empleadoDato.setIdLogin(DatoLogin.getId());
				
		Empleado empleadoGuardado=empleadoRespository.save(empleadoDato);
		
				
		return ResponseEntity.status(HttpStatus.OK).body(empleadoDTO);
	}

}
