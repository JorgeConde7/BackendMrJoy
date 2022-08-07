package com.example.demo.dto;

public class DetalleBoletaDTO {
	
	private int idDetalle;
	private int numBoleta;
	private int idTipoEntrada;
	private int cantidad;
	private double subTotal;
	
	public DetalleBoletaDTO() {
		super();
	}
	
	public DetalleBoletaDTO(int idDetalle, int numBoleta, int idTipoEntrada, int cantidad, double subTotal) {
		super();
		this.idDetalle = idDetalle;
		this.numBoleta = numBoleta;
		this.idTipoEntrada = idTipoEntrada;
		this.cantidad = cantidad;
		this.subTotal = subTotal;
	}

	public int getIdDetalle() {
		return idDetalle;
	}
	public void setIdDetalle(int idDetalle) {
		this.idDetalle = idDetalle;
	}
	public int getNumBoleta() {
		return numBoleta;
	}
	public void setNumBoleta(int numBoleta) {
		this.numBoleta = numBoleta;
	}
	public int getIdTipoEntrada() {
		return idTipoEntrada;
	}
	public void setIdTipoEntrada(int idTipoEntrada) {
		this.idTipoEntrada = idTipoEntrada;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public double getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}

	
	
	

}
