package com.example.demo.service.impl;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
		Date fechaReserva = reserva.getFechaReserva();
		Calendar c = Calendar.getInstance();
		c.setTime(fechaReserva);
		c.add(Calendar.DATE, 1);
		java.util.Date dates = c.getTime();
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String format = formatter.format(dates);
		System.out.println(format);
		reserva.setFechaReserva(Date.valueOf(format));
		reserva.setFechaRegistro(ObtenerFechaActual());
		return reservaRepository.save(reserva);
	}
	
	public Date ObtenerFechaActual()
	{
		long miliseconds = System.currentTimeMillis();
        Date date = new Date(miliseconds);
		return date;
	}

	@Override
	public void delete(Long id) {
		reservaRepository.deleteById(id);
	}

	@Override
	public List<Reserva> ListarPorFecha(Date fechaReserva) {
		return(List<Reserva>) reservaRepository.findByfechaReserva(fechaReserva);
	}

	

}
