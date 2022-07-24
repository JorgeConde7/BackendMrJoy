package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.BoletaEntrada;

import com.example.demo.repository.BoletaRepository;
import com.example.demo.service.BoletaService;

@Service
public class BoletaServiceImpl implements BoletaService{

	@Autowired
	private BoletaRepository boletaRepository;
	
	@Override
	@Transactional
	public List<BoletaEntrada> listarBoleta() throws Exception {
		try{
            List<BoletaEntrada> boleta= boletaRepository.findAll();
            return boleta;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
	}

	@Override
	@Transactional
	public BoletaEntrada findById(Integer id) throws Exception {
		try{
            Optional<BoletaEntrada> boletaOptional= boletaRepository.findById(id);
            return boletaOptional.get();
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
	}

	@Override
	@Transactional
	public BoletaEntrada save(BoletaEntrada model) throws Exception {
		try{
            model=boletaRepository.save(model);
            return model;

        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
	}

	@Override
	@Transactional
	public BoletaEntrada update(Integer id, BoletaEntrada model) throws Exception {
		try{
            Optional<BoletaEntrada> boletaOptional= boletaRepository.findById(id);
            BoletaEntrada boleta= boletaOptional.get();
            model.setNumBoleta(id);
            boleta=boletaRepository.save(model);
            
            return boleta;
        }catch (Exception e){
            throw new Exception(e.getMessage());
            
        }
	}

	@Override
	@Transactional
	public boolean delete(Integer id) throws Exception {
		 try {
	            if (boletaRepository.existsById(id)) {
	            	boletaRepository.deleteById(id);
	                return true;
	            } else {
	                throw new Exception();
	            }
	        } catch (Exception e) {
	            throw new Exception(e.getMessage());
	        }
	}

}
