package com.cenfotec.cenfoteca.controllers;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cenfotec.cenfoteca.contracts.TipoUsuarioRequest;
import com.cenfotec.cenfoteca.contracts.TipoUsuarioResponse;
import com.cenfotec.cenfoteca.services.TipoUsuarioServiceInterface;

@RestController
@RequestMapping(value ="rest/protected/tipoUsuario")
public class TipoUsuarioController {
	
	@Autowired private TipoUsuarioServiceInterface tipoUsuarioService;
	
	@RequestMapping(value ="/create", method = RequestMethod.POST)
	public TipoUsuarioResponse create(@RequestBody TipoUsuarioRequest tur){	
		
		TipoUsuarioResponse us = new TipoUsuarioResponse();
		Boolean state = tipoUsuarioService.saveTipoUsuario(tur);
	
		if(state){
			us.setCode(200);
			us.setCodeMessage("tipo usuario creado exitosamente");
		}
		return us;	
	}
	
	@RequestMapping(value ="/getAll", method = RequestMethod.GET)
	public TipoUsuarioResponse getAll(){	
			
		TipoUsuarioResponse response = new TipoUsuarioResponse();
		response.setCode(200);
		response.setCodeMessage("users fetch success");
		response.setTipoUsuarioList(tipoUsuarioService.getAll());
		return response;		
	}

}
