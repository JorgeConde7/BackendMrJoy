package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.BoletaDTO;
import com.example.demo.dto.DetalleBoletaDTO;
import com.example.demo.exception.ErrorException;
import com.example.demo.exception.MrJoyException;
import com.example.demo.model.BoletaEntrada;
import com.example.demo.model.DetalleBoleta;
import com.example.demo.model.Login;
import com.example.demo.repository.BoletaRepository;
import com.example.demo.repository.DetalleBoletaRepository;
import com.example.demo.repository.LoginRepository;
import com.example.demo.service.BoletaService;
import com.example.demo.util.Constantes;
import com.example.demo.util.Utilitarios;

@Service
public class BoletaServiceImpl implements BoletaService{

	@Autowired
	private BoletaRepository boletaRepository;
	@Autowired
	private DetalleBoletaRepository detalleBoletaRepository;
	@Autowired
	private LoginRepository loginRepository;
	
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
		
		Utilitarios utilitarios= null;
		try {
			BoletaEntrada boletaEntrada= new BoletaEntrada();
			Login login= new Login();

			utilitarios= new Utilitarios();
			boletaEntrada.setFechaRegistro(utilitarios.ObtenerFechaActual());
			boletaEntrada.setIdLogin(boletaDTO.getidLogin());
			
			long id=boletaDTO.getidLogin();
			Optional<Login> datosLogin=loginRepository.findById(id);
			login=datosLogin.get();
			
			if(login.getTipouser().equals(Constantes.VALOR_CLIENTE)) {
				boletaEntrada.setFlagTipoBoleta(Constantes.FLAG_CLIENTE);
			}
			if(login.getTipouser().equals(Constantes.VALOR_EMPLEADO)) {
				boletaEntrada.setFlagTipoBoleta(Constantes.FlAG_EMPLEADO);	
			}
					
			boletaEntrada.setTotal(boletaDTO.getTotal());
			BoletaEntrada datosBoleta =boletaRepository.save(boletaEntrada);

			List<DetalleBoletaDTO> listDetalleBoletas = boletaDTO.getDetalleBoleta();
			DetalleBoleta detBoleta=null;
			
			for (int i = 0; i < listDetalleBoletas.size(); i++) {
				detBoleta=new DetalleBoleta();
				detBoleta.setNumBoleta(datosBoleta.getNumBoleta());
				detBoleta.setCantidad(listDetalleBoletas.get(i).getCantidad());
				detBoleta.setPrecioUnitario(listDetalleBoletas.get(i).getSubTotal());
				detBoleta.setIdTipoEntrada(listDetalleBoletas.get(i).getIdTipoEntrada());
				detalleBoletaRepository.save(detBoleta);
			}
			
			return boletaDTO;
		}catch(MrJoyException e) {
			throw e;
		}catch(Exception e) {
			throw new ErrorException();
		}
		
	}

}
