package com.cenfotec.cenfoteca.services;

import java.util.List;

import com.cenfotec.cenfoteca.ejb.TipoAlquiler;
import com.cenfotec.cenfoteca.ejb.TipoUsuario;

public interface GeneralServiceInterface {

	List<TipoUsuario> getAllTipoUsuario();
	TipoUsuario getTipoUsuarioById(Integer idTipoUsuario);
	List<TipoAlquiler> getAllTipoAlquiler();
	TipoAlquiler getTipoAlquilerById(Integer idTipoAlquiler);
}
