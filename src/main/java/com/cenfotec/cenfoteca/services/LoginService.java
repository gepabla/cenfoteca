package com.cenfotec.cenfoteca.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cenfotec.cenfoteca.contracts.LoginRequest;
import com.cenfotec.cenfoteca.ejb.Usuario;
import com.cenfotec.cenfoteca.repositories.LoginRepository;

@Service
public class LoginService implements LoginServiceInterface{

	@Autowired
	LoginRepository loginRepository;
	
	@Override
	@Transactional
	public Usuario checkUser(LoginRequest lr) {
		return loginRepository.findByEmailAndPassword(lr.getEmail(), lr.getPassword());
	}		
}