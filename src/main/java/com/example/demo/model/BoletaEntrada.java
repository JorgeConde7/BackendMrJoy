package com.example.demo.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="boleto_entrada")
public class BoletaEntrada {
	
	@Id
	private int numBoleta;
	
	@Column(name="fechaRegistro")
	private Date fechaRegistro;
	
	@Column(name="total")
	private Double total;
	
	@Column(name="idLogin")
	private int idLogin;
	
	@Column(name="flagTipoBoleta")
	private String flagTipoBoleta;
	
	public int getNumBoleta() {
		return numBoleta;
	}

	public void setNumBoleta(int numBoleta) {
		this.numBoleta = numBoleta;
	}

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public int getIdLogin() {
		return idLogin;
	}

	public void setIdLogin(int idLogin) {
		this.idLogin = idLogin;
	}

	public String getFlagTipoBoleta() {
		return flagTipoBoleta;
	}

	public void setFlagTipoBoleta(String flagTipoBoleta) {
		this.flagTipoBoleta = flagTipoBoleta;
	}

	

	
	
}