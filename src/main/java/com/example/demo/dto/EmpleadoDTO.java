package com.example.demo.dto;

import java.sql.Date;

public class EmpleadoDTO {

	private Long id_empleados;
	private String nombres;
	private String apellidos;
	private String telefono;
	private String correo;
	private String turno;
	private String dni;
	private Date fechaNacimiento;
	private Long idLogin;
	private String usuario;
	private String contrasenia;
	private String tipouser;
		
	public EmpleadoDTO() {
		
	}
	
	public EmpleadoDTO(Long id_empleados, String nombres, String apellidos, String telefono,String dni, String correo,
			String turno, Long idLogin, Date fechaNacimiento, String usuario, String contrasenia, String tipouser) {
		super();
		this.id_empleados = id_empleados;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.telefono = telefono;
		this.correo = correo;
		this.turno = turno;
		this.dni=dni;
		this.idLogin = idLogin;
		this.fechaNacimiento = fechaNacimiento;
		this.usuario = usuario;
		this.contrasenia = contrasenia;
		this.tipouser = tipouser;
	}
	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public Long getId_empleados() {
		return id_empleados;
	}
	public void setId_empleados(Long id_empleados) {
		this.id_empleados = id_empleados;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getTurno() {
		return turno;
	}
	public void setTurno(String turno) {
		this.turno = turno;
	}
	public Long getIdLogin() {
		return idLogin;
	}
	public void setIdLogin(Long idLogin) {
		this.idLogin = idLogin;
	}
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getContrasenia() {
		return contrasenia;
	}
	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}
	public String getTipouser() {
		return tipouser;
	}
	public void setTipouser(String tipouser) {
		this.tipouser = tipouser;
	}
	
	
}
