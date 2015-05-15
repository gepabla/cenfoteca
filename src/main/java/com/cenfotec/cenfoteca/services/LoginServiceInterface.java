package com.cenfotec.cenfoteca.services;

import com.cenfotec.cenfoteca.contracts.LoginRequest;
import com.cenfotec.cenfoteca.ejb.Usuario;

public interface LoginServiceInterface {

	Usuario checkUser(LoginRequest lr);

}
