package com.cenfotec.cenfoteca.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cenfotec.cenfoteca.contracts.TipoUsuarioResponse;
import com.cenfotec.cenfoteca.ejb.TipoUsuario;
import com.cenfotec.cenfoteca.services.TipoUsuarioServiceInterface;

@RestController
@RequestMapping(value ="rest/protected/tipoUsuario")
public class TipoUsuarioController {
	
	@Autowired private TipoUsuarioServiceInterface tipoUsuarioService;
	
	@RequestMapping(value ="/create", method = RequestMethod.POST)
	public TipoUsuarioResponse create(
			@RequestParam("idTipoUsuario") int idTipoUsuario,
			@RequestParam("tipo") String tipo){	
		
		TipoUsuarioResponse rs = new TipoUsuarioResponse();
	
		if(!tipo.equals("")){
			
			TipoUsuario tpUsuario = new TipoUsuario();
			tpUsuario.setIdTipoUsuario(idTipoUsuario);
			tpUsuario.setTipo(tipo);
			
			Boolean state = tipoUsuarioService.saveTipoUsuario(tpUsuario);
			
			if(state){
				rs.setCode(200);
				rs.setCodeMessage("Tipo usuario creado exitosamente");
			}
			
		}else{
			//create a common webservice error codes enum or statics
			rs.setCode(409);
			rs.setErrorMessage("create/edit conflict");
		}
	
		return rs;	
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
