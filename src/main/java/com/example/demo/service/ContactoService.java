package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Contacto;

public interface ContactoService {
	
	public List<Contacto> findAll();
	public Contacto findById (Long Id);
	public Contacto save (Contacto contacto);
	public void delete (Long Id);

}
