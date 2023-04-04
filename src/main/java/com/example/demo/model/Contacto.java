package com.example.demo.model;

import java.sql.Date;

import javax.persistence.*;


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
	private String descripcion;
	private Date fechaRegistro;
	private String estado;
	private String usuarioAtencion;
	private Date fechaAtencion;
	private String respuestaAtencion;
	private Long idLogin;

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

	public String getUsuarioAtencion() {
		return usuarioAtencion;
	}

	public void setUsuarioAtencion(String usuarioAtencion) {
		this.usuarioAtencion = usuarioAtencion;
	}

	public Date getFechaAtencion() {
		return fechaAtencion;
	}
	public void setFechaAtencion(Date fechaAtencion) {
		this.fechaAtencion = fechaAtencion;
	}
	public String getRespuestaAtencion() {
		return respuestaAtencion;
	}

	public void setRespuestaAtencion(String respuestaAtencion) {
		this.respuestaAtencion = respuestaAtencion;
	}
	public Long getIdLogin() {
		return idLogin;
	}
	public void setIdLogin(Long idLogin) {
		this.idLogin = idLogin;
	}




	
	
}
