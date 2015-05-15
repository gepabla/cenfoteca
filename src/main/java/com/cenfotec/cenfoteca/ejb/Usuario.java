package com.cenfotec.cenfoteca.ejb;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the usuario database table.
 * 
 */
@Entity
@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idUsuario;

	private String email;

	private String firstname;

	private String lastname;

	private String password;

	//bi-directional many-to-one association to TipoUsuario
	@ManyToOne
	@JoinColumn(name="tipo_usuario_idTipoUsuario")
	private TipoUsuario tipoUsuario;

	//bi-directional many-to-many association to Alquiler
	@ManyToMany
	@JoinTable(
		name="usuario_has_alquiler"
		, joinColumns={
			@JoinColumn(name="usuario_idUsuario")
			}
		, inverseJoinColumns={
			@JoinColumn(name="alquiler_idAlquiler")
			}
		)
	private List<Alquiler> alquilers;

	public Usuario() {
	}

	public int getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public TipoUsuario getTipoUsuario() {
		return this.tipoUsuario;
	}

	public void setTipoUsuario(TipoUsuario tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public List<Alquiler> getAlquilers() {
		return this.alquilers;
	}

	public void setAlquilers(List<Alquiler> alquilers) {
		this.alquilers = alquilers;
	}

}