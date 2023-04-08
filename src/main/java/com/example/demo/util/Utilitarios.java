package com.example.demo.util;

import java.sql.Date;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Utilitarios {
	
	public Date ObtenerFechaActual()
	{
		long miliseconds = System.currentTimeMillis();
        Date date = new Date(miliseconds);
		return date;
	}
	
	public String ObtenerHoraActual() {
		LocalTime horaActual = LocalTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
		String horaFormateada = horaActual.format(formatter);// Obtiene la hora actual del sistema
		System.out.println(horaFormateada);
		return horaFormateada;
	}

}
