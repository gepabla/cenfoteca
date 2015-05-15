package com.cenfotec.cenfoteca.repositories;

import org.springframework.data.repository.CrudRepository;

import com.cenfotec.cenfoteca.ejb.TipoUsuario;

public interface GeneralRepository extends CrudRepository<TipoUsuario,Integer> {
	
	public static final int PAGE_SIZE = 5;
}
