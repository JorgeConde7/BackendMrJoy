package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.DetalleBoleta;
import com.example.demo.repository.DetalleBoletaRepository;
import com.example.demo.service.DetalleBoletaService;

@Service
public class DetalleBoletaServiceImpl implements DetalleBoletaService {

	@Autowired
	private DetalleBoletaRepository detalleBoletaRepository;
	
	@Override
	@Transactional
	public List<DetalleBoleta> listarBoleta() throws Exception {
		try{
            List<DetalleBoleta> detalleBoleta= detalleBoletaRepository.findAll();
            return detalleBoleta;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
	}

	@Override
	@Transactional
	public DetalleBoleta findById(Integer id) throws Exception {
		try{
            Optional<DetalleBoleta> boletaOptional= detalleBoletaRepository.findById(id);
            return boletaOptional.get();
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
	}

	@Override
	@Transactional
	public DetalleBoleta save(DetalleBoleta model) throws Exception {
		try{
            model=detalleBoletaRepository.save(model);
            return model;

        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
	}

	@Override
	@Transactional
	public DetalleBoleta update(Integer id, DetalleBoleta model) throws Exception {
		try{
            Optional<DetalleBoleta> boletaOptional= detalleBoletaRepository.findById(id);
            DetalleBoleta boleta= boletaOptional.get();
            model.setNumBoleta(id);
            boleta=detalleBoletaRepository.save(model);
            
            return boleta;
        }catch (Exception e){
            throw new Exception(e.getMessage());
            
        }
	}

	@Override
	@Transactional
	public boolean delete(Integer id) throws Exception {
		try {
            if (detalleBoletaRepository.existsById(id)) {
            	detalleBoletaRepository.deleteById(id);
                return true;
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
	}

}
