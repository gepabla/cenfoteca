package com.cenfotec.cenfoteca.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cenfotec.cenfoteca.contracts.TipoAlquilerResponse;
import com.cenfotec.cenfoteca.contracts.TipoUsuarioResponse;
import com.cenfotec.cenfoteca.services.TipoAlquilerServiceInterface;
import com.cenfotec.cenfoteca.services.TipoUsuarioServiceInterface;
import com.cenfotec.cenfoteca.services.UsersServiceInterface;

@RestController
@RequestMapping(value ="rest/protected/tipoUsuario")
public class TipoUsuarioController {
	
	@Autowired private UsersServiceInterface usersService;
	@Autowired private TipoUsuarioServiceInterface tipoUsuarioService;
	@Autowired private HttpServletRequest request;
	
	@RequestMapping(value ="/getAll", method = RequestMethod.GET)
	public TipoUsuarioResponse getAll(){	
			
		TipoUsuarioResponse response = new TipoUsuarioResponse();
		response.setCode(200);
		response.setCodeMessage("users fetch success");
		response.setTipoUsuarioList(tipoUsuarioService.getAll());
		return response;		
	}

}
