package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Parque;
import com.example.demo.repository.PaqueteRepository;
import com.example.demo.repository.ParquesRepository;
import com.example.demo.service.ParquesService;

@Service
public class ParquesServiceImpl implements ParquesService{
	
	@Autowired
	private ParquesRepository parquesRepository;

	@Override
	public List<Parque> listarParques() throws Exception {
		try {
			List<Parque> parques= parquesRepository.findAll();
			return parques;
		}catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public Parque guardarParque(Parque model) throws Exception {
		try {
			return parquesRepository.save(model);
		}catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public Parque actualizarParque(Integer id, Parque model) throws Exception {
		try {
			Optional<Parque> parquetOptional= parquesRepository.findById(id);
			Parque parque= parquetOptional.get();
			model.setIdParque(id);
			
			parque = parquesRepository.save(model);
				return parque;
		}catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public void eliminarParque(Integer id) throws Exception {
		try {
			parquesRepository.deleteById(id);
		}catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

}
