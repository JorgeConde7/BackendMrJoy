package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Reserva;
import com.example.demo.repository.ReservaRepository;
import com.example.demo.service.ReservaService;

@Service
public class ReservaServiceImpl implements ReservaService{

	@Autowired
	private ReservaRepository reservaRepository;
	
	@Override
	@Transactional(readOnly=true)
	public List<Reserva> findAll() {
		return(List<Reserva>) reservaRepository.findAll();
	}

	@Override
	public Reserva findById(Long id) {
		return reservaRepository.findById(id).orElse(null);
	}

	@Override
	public Reserva save(Reserva reserva) {
		return reservaRepository.save(reserva);
	}

	@Override
	public void delete(Long id) {
		reservaRepository.deleteById(id);
	}

}
