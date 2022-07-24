package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.DetalleBoleta;



public interface DetalleBoletaService {
	
	List<DetalleBoleta> listarBoleta() throws Exception;
		
	DetalleBoleta findById(Integer id) throws Exception;
	
	DetalleBoleta save(DetalleBoleta model) throws Exception;
	
	DetalleBoleta update(Integer id, DetalleBoleta model) throws Exception;

    boolean delete(Integer id) throws Exception;

}
