package com.example.demo.model;

import java.util.Date;

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
	private Long id_reserva;
	private Date fecha_registro;
	private Date fecha_reserva;
	private String hora;
	private int cant_personas;
	private int id_login;
	private String nombres;
	private String apellido;
	private String telefono;
	private String flag_tipo_reserva;
	
	public Long getId_reserva() {
		return id_reserva;
	}
	public void setId_reserva(Long id_reserva) {
		this.id_reserva = id_reserva;
	}
	public Date getFecha_registro() {
		return fecha_registro;
	}
	public void setFecha_registro(Date fecha_registro) {
		this.fecha_registro = fecha_registro;
	}
	public Date getFecha_reserva() {
		return fecha_reserva;
	}
	public void setFecha_reserva(Date fecha_reserva) {
		this.fecha_reserva = fecha_reserva;
	}
	public String getHora() {
		return hora;
	}
	public void setHora(String hora) {
		this.hora = hora;
	}
	public int getCant_personas() {
		return cant_personas;
	}
	public void setCant_personas(int cant_personas) {
		this.cant_personas = cant_personas;
	}
	public int getId_login() {
		return id_login;
	}
	public void setId_login(int id_login) {
		this.id_login = id_login;
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
	public String getFlag_tipo_reserva() {
		return flag_tipo_reserva;
	}
	public void setFlag_tipo_reserva(String flag_tipo_reserva) {
		this.flag_tipo_reserva = flag_tipo_reserva;
	}
	public static Long getSerialversionuid() {
		return serialVersionUID;
	}
	
	/*@Column(name="fecha_registro")
	@Temporal(TemporalType.DATE) //Formato a guardar:
	private Date fecha_registro;
	
	@PrePersist
	public void prePersist() 
	{
		fecha_registro = new Date();
	}*/

	
	
	
	
}
