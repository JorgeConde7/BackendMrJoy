package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="parque")
public class Parque {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int idParque;
	@Column(name="nombre")
	private String nombre;
	@Column(name="horario1")
	private String horario1;
	@Column(name="horario2")
	private String horario2;
	@Column(name="rutaImg")
	private String rutaImg;
	
	
	public Parque(int idParque, String nombre, String horario1, String horario2, String rutaImg) {
		super();
		this.idParque = idParque;
		this.nombre = nombre;
		this.horario1 = horario1;
		this.horario2 = horario2;
		this.rutaImg = rutaImg;
	}
	
	public int getIdParque() {
		return idParque;
	}
	public void setIdParque(int idParque) {
		this.idParque = idParque;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getHorario1() {
		return horario1;
	}
	public void setHorario1(String horario1) {
		this.horario1 = horario1;
	}
	public String getHorario2() {
		return horario2;
	}
	public void setHorario2(String horario2) {
		this.horario2 = horario2;
	}
	public String getRutaImg() {
		return rutaImg;
	}
	public void setRutaImg(String rutaImg) {
		this.rutaImg = rutaImg;
	}
	
	
}
