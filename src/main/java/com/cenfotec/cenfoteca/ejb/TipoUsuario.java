package com.cenfotec.cenfoteca.ejb;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQuery(name="TipoUsuario.findAll", query="SELECT t FROM TipoUsuario t")
public class TipoUsuario implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idTipoUsuario;

	private String tipo;

	//bi-directional many-to-one association to Usuario
	@OneToMany(mappedBy="tipoUsuario")
	private List<Usuario> usuarios;

	public TipoUsuario() {
	}

	public int getIdTipoUsuario() {
		return this.idTipoUsuario;
	}

	public void setIdTipoUsuario(int idTipoUsuario) {
		this.idTipoUsuario = idTipoUsuario;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public List<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(List<Usuario> Usuarios) {
		this.usuarios = Usuarios;
	}

	public Usuario addUsuario(Usuario Usuario) {
		getUsuarios().add(Usuario);
		Usuario.setTipoUsuario(this);

		return Usuario;
	}

	public Usuario removeUsuario(Usuario Usuario) {
		getUsuarios().remove(Usuario);
		Usuario.setTipoUsuario(null);

		return Usuario;
	}

}
