package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Contacto;
import com.example.demo.repository.ContactoRepository;
import com.example.demo.service.ContactoService;

@Service
public class ContactoServiceImpl implements ContactoService {
	
	@Autowired
	private ContactoRepository contactoRepository;

	@Override
	public List<Contacto> findAll() {
		return (List<Contacto>) contactoRepository.findAll();
	}

	@Override
	public Contacto findById(Long Id) {
		return contactoRepository.findById(Id).orElse(null);
	}

	@Override
	public Contacto save(Contacto contacto) {
		return contactoRepository.save(contacto);
	}

	@Override
	public void delete(Long Id) {
		contactoRepository.deleteById(Id);
	}

}
