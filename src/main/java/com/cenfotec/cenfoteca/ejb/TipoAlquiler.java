package com.cenfotec.cenfoteca.ejb;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tipo_alquiler database table.
 * 
 */
@Entity
@Table(name="tipo_alquiler")
@NamedQuery(name="TipoAlquiler.findAll", query="SELECT t FROM TipoAlquiler t")
public class TipoAlquiler implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idTipoAlquiler;

	private String tipo;

	//bi-directional many-to-one association to Alquiler
	@OneToMany(mappedBy="tipoAlquiler")
	private List<Alquiler> alquilers;

	public TipoAlquiler() {
	}

	public int getIdTipoAlquiler() {
		return this.idTipoAlquiler;
	}

	public void setIdTipoAlquiler(int idTipoAlquiler) {
		this.idTipoAlquiler = idTipoAlquiler;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public List<Alquiler> getAlquilers() {
		return this.alquilers;
	}

	public void setAlquilers(List<Alquiler> alquilers) {
		this.alquilers = alquilers;
	}

	public Alquiler addAlquiler(Alquiler alquiler) {
		getAlquilers().add(alquiler);
		alquiler.setTipoAlquiler(this);

		return alquiler;
	}

	public Alquiler removeAlquiler(Alquiler alquiler) {
		getAlquilers().remove(alquiler);
		alquiler.setTipoAlquiler(null);

		return alquiler;
	}

}