package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Empleado;
import com.example.demo.repository.EmpleadoRespository;
import com.example.demo.service.EmpleadoService;
@Service
public class EmpleadoServceImpl implements EmpleadoService {
	
	@Autowired
	private EmpleadoRespository empleadoRespository;
	@Override

	@Transactional(readOnly=true)
	public List<Empleado> findAll() {
		// TODO Auto-generated method stub
		return (List<Empleado>)empleadoRespository.findAll();
	}
	@Transactional
	@Override
	public Empleado findById(Long id) {
		// TODO Auto-generated method stub
		return empleadoRespository.findById(id).orElse(null);
	}
	@Transactional
	@Override
	public Empleado save(Empleado empleado) {
		// TODO Auto-generated method stub
		return empleadoRespository.save(empleado);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		// TODO Auto-generated method stub
		empleadoRespository.deleteById(id);
	}

}
