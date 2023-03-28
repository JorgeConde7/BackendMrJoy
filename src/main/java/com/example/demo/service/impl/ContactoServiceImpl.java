package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.ErrorException;
import com.example.demo.exception.MrJoyException;
import com.example.demo.model.Contacto;
import com.example.demo.model.Empleado;
import com.example.demo.repository.ContactoRepository;
import com.example.demo.repository.EmpleadoRespository;
import com.example.demo.service.ContactoService;
import com.example.demo.util.Constantes;
import com.example.demo.util.Utilitarios;

@Service
public class ContactoServiceImpl implements ContactoService {
	
	Utilitarios utilitarios= new Utilitarios();
	
	@Autowired
	private ContactoRepository contactoRepository;

	@Autowired
	private EmpleadoRespository empleadoRespository;
	
	@Override
	public List<Contacto> findAll() throws Exception {
		return (List<Contacto>) contactoRepository.findAll();
	}

	@Override
	public List<Contacto> listarXidLogin(Long idLogin) throws Exception {
		return(List<Contacto>) contactoRepository.findByIdLogin(idLogin);
	}
	
	@Override
	public Contacto save(Contacto contacto) throws Exception {
		contacto.setFechaRegistro(utilitarios.ObtenerFechaActual());
		contacto.setEstado(Constantes.ESTADO_REGISTRADO);

		return contactoRepository.save(contacto);
	}

	@Override
	public Contacto responder(Contacto model,Long idContacto) throws Exception {
		try {
			
				Optional<Contacto> contactoActual= contactoRepository.findById(idContacto);
				Contacto contacto= contactoActual.get();
				
			if(contacto.getEstado()==null) {
				contacto.setEstado(Constantes.ESTADO_RESUELTO);
				contacto.setRespuestaAtencion(model.getRespuestaAtencion());
				contacto.setFechaAtencion(utilitarios.ObtenerFechaActual());
				
				Empleado empleado =empleadoRespository.findByIdLogin(model.getIdLogin());
				contacto.setUsuarioAtencion(empleado.getNombres()+" "+empleado.getApellidos());
				
				return contactoRepository.save(contacto);
			}else {
				throw  new MrJoyException("COD06","Este ticket ya fue respondido");
			}
			
			
		}catch (MrJoyException e) {
			throw e;
		}catch(Exception e) {
			throw new ErrorException();
		}
		
	}

	

}
