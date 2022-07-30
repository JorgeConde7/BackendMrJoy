package com.example.demo.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="login")
@Entity
public class Login implements Serializable {
	
	private static final Long serialVersionUID=1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idLogin;
	
	@Column(name="usuario")
	private String usuario;
	
	@Column(name="contrasenia")
	private String contrasenia;
	
	@Column(name="tipouser")
	private String tipouser;
	
	
	
	public String getTipouser() {
		return tipouser;
	}
	public void setTipouser(String tipouser) {
		this.tipouser = tipouser;
	}

	public Long getIdLogin() {
		return idLogin;
	}
	public void setIdLogin(Long idLogin) {
		this.idLogin = idLogin;
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

}
