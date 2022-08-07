package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Cupon;



public interface CuponService {
	public List<Cupon> findAll();
	public Cupon findById (Long Id);
	public Cupon save (Cupon cupon);
	public void delete (Long Id);
}
