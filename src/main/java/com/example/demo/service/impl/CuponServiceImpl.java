package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Cupon;
import com.example.demo.model.Promociones;
import com.example.demo.repository.CuponRepository;

import com.example.demo.service.CuponService;
@Service
public class CuponServiceImpl implements CuponService {

	@Autowired
	private CuponRepository cuponRepository;
	@Override
	@Transactional(readOnly=true)
	public List<Cupon> findAll() {
		// TODO Auto-generated method stub
		return (List<Cupon>)cuponRepository.findAll();
	}

	@Transactional
	@Override
	public Cupon findById(Long id) {
		// TODO Auto-generated method stub
		return cuponRepository.findById(id).orElse(null);
	}

	@Transactional
	@Override
	public Cupon save(Cupon cupon) {
		// TODO Auto-generated method stub
		return cuponRepository.save(cupon);
	}
	@Override
	@Transactional
	public void delete(Long id) {
		// TODO Auto-generated method stub
		cuponRepository.deleteById(id);
	}

}
