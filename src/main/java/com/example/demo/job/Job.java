package com.example.demo.job;

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
	
	@Scheduled(cron = "*/10 * * * * *", zone = "GMT-5") // se ejecutará todos los días a las 22:00 pm
	public void actualizarEstadoReservas() {
		System.out.println("entro EL JOB KSM");
		Utilitarios utilitarios= new Utilitarios();
	    List<Reserva> reservas = reservaRepository.findByfechaReservaAndEstado(utilitarios.ObtenerFechaActual(), Constantes.ESTADO_RESERVA_VIGENTE);
	    for (Reserva reserva : reservas) {
	            reserva.setEstado(Constantes.ESTADO_RESERVA_CADUCADO);
	            reservaRepository.save(reserva);
	    }
	    System.out.println("PASO EL JOB KSM");
	}
}
	
