package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Paquetes;
import com.example.demo.repository.PaqueteRepository;
import com.example.demo.service.PaqueteService;

@Service
public class PaqueteServiceImpl implements PaqueteService {
	
	@Autowired
	private PaqueteRepository paqueteRepository;

	@Override
	public List<Paquetes> listarPaquetes() throws Exception {
		try{
            List<Paquetes> boleta= paqueteRepository.findAll();
            return boleta;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
	}

}