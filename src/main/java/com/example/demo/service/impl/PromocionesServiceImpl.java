package com.example.demo.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.example.demo.model.Promociones;
import com.example.demo.repository.PromocionesRepository;
import com.example.demo.service.PromocionesService;
@Service
public class PromocionesServiceImpl implements PromocionesService {
	@Autowired
	private PromocionesRepository promocionesrepository;
	@Override
	@Transactional(readOnly=true)
	public List<Promociones> findAll() {
		// TODO Auto-generated method stub
		return (List<Promociones>)promocionesrepository.findAll();
	}
	@Transactional
	@Override
	public Promociones findById(Long id) {
		// TODO Auto-generated method stub
		return promocionesrepository.findById(id).orElse(null);
	}
	@Transactional
	@Override
	public Promociones save(Promociones promociones) {
		// TODO Auto-generated method stub
		return promocionesrepository.save(promociones);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		// TODO Auto-generated method stub
		promocionesrepository.deleteById(id);
	}
	

}
