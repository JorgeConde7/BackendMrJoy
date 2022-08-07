package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="promociones")
public class Promociones {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_promociones;
	private String promociones;
	private String descripcion;
	private String foto;
	public Long getId_promociones() {
		return id_promociones;
	}
	public void setId_promociones(Long id_promociones) {
		this.id_promociones = id_promociones;
	}
	public String getPromociones() {
		return promociones;
	}
	public void setPromociones(String promociones) {
		this.promociones = promociones;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

}
