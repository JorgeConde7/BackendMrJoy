package com.example.demo.model;

import java.sql.Date;

//import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
//import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="reservas")
public class Reserva 
{
	private static final Long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idReserva;
	@Column(name="fecha_registro")
	private Date fechaRegistro;
	@Column(name="fecha_reserva")
	private Date fechaReserva;
	@Column(name="hora")
	private String hora;
	@Column(name="cant_personas")
	private int cantPersonas;
	@Column(name="id_login")
	private int idLogin;
	@Column(name="nombres")
	private String nombres;
	@Column(name="apellido")
	private String apellido;
	@Column(name="telefono")
	private String telefono;
	@Column(name="flag_tipo_reserva")
	private String flagTipoReserva;
	@Column(name="id_paquete")
	private int idPaquete;
	@Column(name="acompaniante")
	private int acompaniante;
	@Column(name="total_pago")
	private double totalPago;
	
	public Long getIdReserva() {
		return idReserva;
	}
	public void setIdReserva(Long idReserva) {
		this.idReserva = idReserva;
	}
	public Date getFechaRegistro() {
		return fechaRegistro;
	}
	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	public Date getFechaReserva() {
		return fechaReserva;
	}
	public void setFechaReserva(Date fechaReserva) {
		this.fechaReserva = fechaReserva;
	}
	public String getHora() {
		return hora;
	}
	public void setHora(String hora) {
		this.hora = hora;
	}
	public int getCantPersonas() {
		return cantPersonas;
	}
	public void setCantPersonas(int cantPersonas) {
		this.cantPersonas = cantPersonas;
	}
	public int getIdLogin() {
		return idLogin;
	}
	public void setIdLogin(int idLogin) {
		this.idLogin = idLogin;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getFlagTipoReserva() {
		return flagTipoReserva;
	}
	public void setFlagTipoReserva(String flagTipoReserva) {
		this.flagTipoReserva = flagTipoReserva;
	}
	public int getIdPaquete() {
		return idPaquete;
	}
	public void setIdPaquete(int idPaquete) {
		this.idPaquete = idPaquete;
	}
	public int getAcompaniante() {
		return acompaniante;
	}
	public void setAcompaniante(int acompaniante) {
		this.acompaniante = acompaniante;
	}
	public double getTotalPago() {
		return totalPago;
	}
	public void setTotalPago(double totalPago) {
		this.totalPago = totalPago;
	}

	
	

	
	
	
	
	
}
