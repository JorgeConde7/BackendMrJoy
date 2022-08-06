package com.example.demo.util;

import java.sql.Date;

public class Utilitarios {
	
	public Date ObtenerFechaActual()
	{
		long miliseconds = System.currentTimeMillis();
        Date date = new Date(miliseconds);
		return date;
	}

}
