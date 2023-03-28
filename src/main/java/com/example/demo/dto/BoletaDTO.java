package com.example.demo.dto;

import java.util.List;

public class BoletaDTO {
	private int numBoleta;
	private double total;
	private int idLogin;
	private List<DetalleBoletaDTO> detalleBoleta;
	
	public BoletaDTO() {
		super();
	}
		
	public BoletaDTO(int numBoleta, double total, int idLogin, List<DetalleBoletaDTO> detalleBoleta) {
		super();
		this.numBoleta = numBoleta;
		this.total = total;
		this.idLogin = idLogin;
		this.detalleBoleta = detalleBoleta;
	}

	public int getNumBoleta() {
		return numBoleta;
	}
	public void setNumBoleta(int numBoleta) {
		this.numBoleta = numBoleta;
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
	public List<DetalleBoletaDTO> getDetalleBoleta() {
		return detalleBoleta;
	}
	public void setDetalleBoleta(List<DetalleBoletaDTO> detalleBoleta) {
		this.detalleBoleta = detalleBoleta;
	}
	
	
	

}
