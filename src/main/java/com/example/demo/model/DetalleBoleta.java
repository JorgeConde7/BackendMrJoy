package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="detalles_boleta")
public class DetalleBoleta {
	@Id
	private int idDetalle;
	@Column(name="numBoleta")
	private int numBoleta;
	@Column(name = "id_tipo_entrada")
	private int idTipoEntrada;
	@Column(name = "cantidad")
	private int cantidad;
	@Column(name = "precio_unitario")
	private double precioUnitario;
	
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
	public double getPrecioUnitario() {
		return precioUnitario;
	}
	public void setPrecioUnitario(double precioUnitario) {
		this.precioUnitario = precioUnitario;
	}
	


}
