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
	private ContactoRepository contactoDao;

	@Override
	public List<Contacto> findAll() {
		return (List<Contacto>) contactoDao.findAll();
	}

	@Override
	public Contacto findById(Long Id) {
		return contactoDao.findById(Id).orElse(null);
	}

	@Override
	public Contacto save(Contacto contacto) {
		return contactoDao.save(contacto);
	}

	@Override
	public void delete(Long Id) {
		contactoDao.deleteById(Id);

	}

}
