package com.example.demo.service.impl;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpServerErrorException;

import com.example.demo.exception.ErrorException;
import com.example.demo.exception.MrJoyException;

import com.example.demo.model.Login;
import com.example.demo.model.Reserva;
import com.example.demo.repository.LoginRepository;
import com.example.demo.repository.ReservaRepository;
import com.example.demo.service.ReservaService;
import com.example.demo.util.Constantes;
import com.example.demo.util.Utilitarios;

import ch.qos.logback.classic.Logger;

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
	public Reserva guardarReserva(Reserva reserva) throws Exception {
		try {
			if(reserva.getCantPersonas()<15){
				throw new MrJoyException("COD02","Las reservas son sólo apartir de 15 personas en adelante");
			}
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
			
			if(login.getTipouser().equals(Constantes.VALOR_CLIENTE)) {
				reserva.setFlagTipoReserva(Constantes.FLAG_CLIENTE);
			}
			if(login.getTipouser().equals(Constantes.VALOR_EMPLEADO) || 
					login.getTipouser().equals(Constantes.VALOR_ADMIN)) {
				reserva.setFlagTipoReserva(Constantes.FlAG_EMPLEADO);	
			}
					
			Utilitarios utilitarios= new Utilitarios();
			if(reserva.getFechaRegistro()==null) {
				reserva.setFechaRegistro(utilitarios.ObtenerFechaActual());
				reserva.setEstado(Constantes.ESTADO_RESERVA_VIGENTE);
			}
			
			reserva.setHasEmail(false);
			
			
			return reservaRepository.save(reserva);
			
		}catch(MrJoyException e) {
			throw e;
		}catch(Exception e) {
			throw new ErrorException();
		}
		
	}
	
	
	@Override
	public List<Reserva> ListarPorFecha(Date fechaReserva) {
		
		List<Reserva> reserva= new ArrayList<>();
		reserva=reservaRepository.findByfechaReservaAndEstado(fechaReserva,Constantes.ESTADO_RESERVA_VIGENTE);
		
		return reserva;
		
	}

	@Override
	public void delete(Long id) {
		reservaRepository.deleteById(id);
	}

	@Override
	public List<Reserva> listarPorIdLogin(int idLogin) {
		return (List<Reserva>) reservaRepository.findByIdLogin(idLogin);
	}

	

}
