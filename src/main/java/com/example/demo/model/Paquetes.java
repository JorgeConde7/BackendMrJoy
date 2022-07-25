package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "paquetes")
public class Paquetes {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int idPaquete;
	@Column(name="descripcion")
	private String descripcion;
	@Column(name="precio")
	private double precio;
	
	public int getIdPaquete() {
		return idPaquete;
	}
	public void setIdPaquete(int idPaquete) {
		this.idPaquete = idPaquete;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
	
	
	
}