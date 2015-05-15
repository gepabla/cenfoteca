package com.cenfotec.cenfoteca.services;

import org.springframework.data.domain.Page;

import com.cenfotec.cenfoteca.contracts.UsersRequest;
import com.cenfotec.cenfoteca.ejb.Usuario;

public interface UsersServiceInterface {

	Page<Usuario> getAll(UsersRequest ur);

	Boolean saveUser(Usuario user);

	Usuario getSessionUser(int idUser);

}
