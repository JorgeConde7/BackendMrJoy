package com.example.demo.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="contacto")

public class Contacto {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long idContacto;
	private String nombres;
	private String correo;
	private String telefono;
	private String asunto;
	private String estado;
	private String descripcion;
	@Temporal(TemporalType.DATE)
	private Date fechaRegistro;

	@PrePersist
	public void prePersist() {
		fechaRegistro = new Date();
	}

	public Date getFechaRegistro() {
		return fechaRegistro;
	}
	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	public Long getIdContacto() {
		return idContacto;
	}
	public void setIdContacto(Long idContacto) {
		this.idContacto = idContacto;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getAsunto() {
		return asunto;
	}
	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}
	public String getEstado() {
		return estado;
	}
	
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
}
