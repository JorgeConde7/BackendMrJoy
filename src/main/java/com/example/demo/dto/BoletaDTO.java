package com.example.demo.dto;

import java.sql.Date;
import java.util.List;

public class BoletaDTO {
	private int numBoleta;
	private Date fechaRegistro;
	private double total;
	private int idLogin;
	private String flagTipoBoleta;
	private List<DetalleBoletaDTO> detalleBoleta;
	
	public BoletaDTO() {
		super();
	}
	
	public BoletaDTO(int numBoleta, Date fechaRegistro, double total, int idLogin, String flagTipoBoleta,
			List<DetalleBoletaDTO> detalleBoleta) {
		super();
		this.numBoleta = numBoleta;
		this.fechaRegistro = fechaRegistro;
		this.total = total;
		this.idLogin = idLogin;
		this.flagTipoBoleta = flagTipoBoleta;
		this.detalleBoleta = detalleBoleta;
	}
	
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
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public int getidLogin() {
		return idLogin;
	}
	public void setidLogin(int idLogin) {
		this.idLogin = idLogin;
	}
	public String getFlagTipoBoleta() {
		return flagTipoBoleta;
	}
	public void setFlagTipoBoleta(String flagTipoBoleta) {
		this.flagTipoBoleta = flagTipoBoleta;
	}
	public List<DetalleBoletaDTO> getDetalleBoleta() {
		return detalleBoleta;
	}
	public void setDetalleBoleta(List<DetalleBoletaDTO> detalleBoleta) {
		this.detalleBoleta = detalleBoleta;
	}
	
	
	

}
