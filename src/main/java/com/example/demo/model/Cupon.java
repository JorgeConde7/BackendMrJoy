package com.example.demo.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="cupon")
public class Cupon {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_cupon;
	private String cupon;
	private int descuento; 
	private int uso;
	private int usoMax;
	private Date fechaRegistro;
	private Date fechaLimite;
	
	
	public int getUso() {
		return uso;
	}
	public void setUso(int uso) {
		this.uso = uso;
	}
	public int getUsoMax() {
		return usoMax;
	}
	public void setUsoMax(int usoMax) {
		this.usoMax = usoMax;
	}
	public Date getFechaRegistro() {
		return fechaRegistro;
	}
	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	public Date getFechaLimite() {
		return fechaLimite;
	}
	public void setFechaLimite(Date fechaLimite) {
		this.fechaLimite = fechaLimite;
	}
	public int getDescuento() {
		return descuento;
	}
	public void setDescuento(int descuento) {
		this.descuento = descuento;
	}
	public Long getId_cupon() {
		return id_cupon;
	}
	public void setId_cupon(Long id_cupon) {
		this.id_cupon = id_cupon;
	}
	public String getCupon() {
		return cupon;
	}
	public void setCupon(String cupon) {
		this.cupon = cupon;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
