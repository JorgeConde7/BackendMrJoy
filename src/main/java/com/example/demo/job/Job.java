package com.example.demo.job;


import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.demo.model.Reserva;
import com.example.demo.repository.ReservaRepository;
import com.example.demo.util.Constantes;
import com.example.demo.util.Utilitarios;

@Component
public class Job {
	
	@Autowired
	private ReservaRepository reservaRepository;
	
	Utilitarios utilitarios= new Utilitarios();
	
	@Scheduled(cron = "0 1-20 9-20 * * *", zone = "GMT-5")   // se ejecutará todo los dias  a las 9 con 1 minuto cada 1 hora
	public void actualizarReservaVigenteaEnCurso() {
		
		String horaActual = utilitarios.ObtenerHoraActual();
	    List<Reserva> reservas = reservaRepository.findByfechaReservaAndEstado(utilitarios.ObtenerFechaActual(), Constantes.ESTADO_RESERVA_VIGENTE);
	    for (Reserva reserva : reservas) {
	    		LocalTime horaReserva = LocalTime.parse(reserva.getHora()); // Convierte la hora de reserva de string a LocalTime
	    		LocalTime horaReservaMasUnMinuto = horaReserva.plusMinutes(1);
	    		
	            if (LocalTime.parse(horaActual).equals(horaReservaMasUnMinuto)) { 
	            	reserva.setEstado(Constantes.ESTADO_RESERVA_EN_CURSO);
	            	reservaRepository.save(reserva);
	            }
	    }
	}


	@Scheduled(cron = "0 0 10-20/1 * * *", zone = "GMT-5") // se ejecutará todo los dias de 10 am a 8 pm
	public void actualizarReservaEnCursoaCaducado() {		
		
	    List<Reserva> reservas = reservaRepository.findByfechaReservaAndEstado(utilitarios.ObtenerFechaActual(), Constantes.ESTADO_RESERVA_EN_CURSO);
	    
	    for (Reserva reserva : reservas) {    	
	            	reserva.setEstado(Constantes.ESTADO_RESERVA_CADUCADO);
	            	reservaRepository.save(reserva);
	            }
	    }
		
}
