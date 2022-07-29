package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.BoletaDTO;
import com.example.demo.dto.DetalleBoletaDTO;
import com.example.demo.model.BoletaEntrada;
import com.example.demo.model.DetalleBoleta;
import com.example.demo.repository.BoletaRepository;
import com.example.demo.repository.DetalleBoletaRepository;
import com.example.demo.service.BoletaService;

@Service
public class BoletaServiceImpl implements BoletaService{

	@Autowired
	private BoletaRepository boletaRepository;
	
	@Autowired
	private DetalleBoletaRepository detalleBoletaRepository;
	
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

	@Override
	public BoletaDTO guardarBoleta(BoletaDTO boletaDTO) throws Exception {
		
		BoletaEntrada boletaEntrada= new BoletaEntrada();
		boletaEntrada.setNumBoleta(boletaDTO.getNumBoleta());
		boletaEntrada.setFechaRegistro(boletaDTO.getFechaRegistro());
		boletaEntrada.setIdLogin(boletaDTO.getidLogin());
		boletaEntrada.setFlagTipoBoleta(boletaDTO.getFlagTipoBoleta());
		boletaEntrada.setTotal(boletaDTO.getTotal());
		BoletaEntrada datosBoleta =boletaRepository.save(boletaEntrada);

		
		List<DetalleBoletaDTO> listDetalleBoletas = boletaDTO.getDetalleBoleta();
		DetalleBoleta detBoleta=null;
		
		for (int i = 0; i < listDetalleBoletas.size(); i++) {
			detBoleta=new DetalleBoleta();
			detBoleta.setNumBoleta(datosBoleta.getNumBoleta());
			detBoleta.setCantidad(listDetalleBoletas.get(i).getCantidad());
			detBoleta.setPrecioUnitario(listDetalleBoletas.get(i).getPrecioUnitario());
			detBoleta.setIdTipoEntrada(listDetalleBoletas.get(i).getIdTipoEntrada());
			detalleBoletaRepository.save(detBoleta);
		}
		/*
		for(DetalleBoletaDTO recorrerBoleta:listDetalleBoletas) {
			
			detBoleta=new DetalleBoleta();
			
			detBoleta.setNumBoleta(datosBoleta.getNumBoleta());
			detBoleta.setCantidad(recorrerBoleta.getCantidad());
			detBoleta.setPrecioUnitario(recorrerBoleta.getPrecioUnitario());
			detBoleta.setIdTipoEntrada(recorrerBoleta.getIdTipoEntrada());
			detalleBoletaRepository.save(detBoleta);
			
		}*/
		

		return boletaDTO;
	}

}
