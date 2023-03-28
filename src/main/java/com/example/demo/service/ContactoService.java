package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Contacto;

public interface ContactoService {
	
	public List<Contacto> findAll() throws Exception;
	public List<Contacto> listarXidLogin (Long idCliente) throws Exception;
	public Contacto save (Contacto contacto) throws Exception;
	public Contacto responder(Contacto contacto, Long idContacto) throws Exception;

}
