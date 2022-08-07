package com.example.demo.service;

import java.util.List;


import com.example.demo.model.Promociones;

public interface PromocionesService {
	public List<Promociones> findAll();
	public Promociones findById (Long Id);
	public Promociones save (Promociones promociones);
	public void delete (Long Id);
}
