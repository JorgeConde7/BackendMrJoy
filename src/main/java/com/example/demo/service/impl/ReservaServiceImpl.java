package com.example.demo.service.impl;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Login;
import com.example.demo.model.Reserva;
import com.example.demo.repository.LoginRepository;
import com.example.demo.repository.ReservaRepository;
import com.example.demo.service.ReservaService;
import com.example.demo.util.Constantes;
import com.example.demo.util.Utilitarios;

@Service
public class ReservaServiceImpl implements ReservaService {
	
	@Autowired
	private ReservaRepository reservaRepository;
	
	@Autowired
	private LoginRepository loginRepository;
	
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
		reserva.setFechaReserva(Date.valueOf(format));
				
		Login login= new Login();
		long id=reserva.getIdLogin();
		Optional<Login> datosLogin=loginRepository.findById(id);
		login=datosLogin.get();
		
		if(login.getTipouser().equals(Constantes.CLIENTE_LOGIN)) {
			reserva.setFlagTipoReserva(Constantes.FLAG_CLIENTE);
		}
		if(login.getTipouser().equals(Constantes.EMPLEADO_LOGIN)) {
			reserva.setFlagTipoReserva(Constantes.FlAG_EMPLEADO);	
		}
				
		Utilitarios utilitarios= new Utilitarios();
		reserva.setFechaRegistro(utilitarios.ObtenerFechaActual());
		return reservaRepository.save(reserva);
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
