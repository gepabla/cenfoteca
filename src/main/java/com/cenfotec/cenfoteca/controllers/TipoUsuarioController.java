package com.cenfotec.cenfoteca.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cenfotec.cenfoteca.contracts.TipoUsuarioResponse;
import com.cenfotec.cenfoteca.ejb.TipoUsuario;
import com.cenfotec.cenfoteca.pojo.TipoUsuarioPOJO;
import com.cenfotec.cenfoteca.services.GeneralServiceInterface;
import com.cenfotec.cenfoteca.utils.PojoUtils;


/**
 * Handles requests for the application home page.
 */
@RestController
@RequestMapping(value ="rest/protected/tipoUsuario")
public class TipoUsuarioController {
	
	@Autowired
	GeneralServiceInterface generalService;
	
	@RequestMapping(value ="/getAll", method = RequestMethod.GET)
	public TipoUsuarioResponse getAll(){
		
		TipoUsuarioResponse tpr = new TipoUsuarioResponse();
		
		List<TipoUsuario> tipoUsuarioList = generalService.getAllTipoUsuario();
		List<TipoUsuarioPOJO> tipoUsuarioViewList = new ArrayList<TipoUsuarioPOJO>();
		
		for (TipoUsuario tp : tipoUsuarioList){
			TipoUsuarioPOJO ntipoUsuario = new TipoUsuarioPOJO();
			PojoUtils.pojoMappingUtility(ntipoUsuario,tp);
			tipoUsuarioViewList.add(ntipoUsuario);
		}
		
		tpr.setTipoUsuarioList(tipoUsuarioViewList);
		
		return tpr;		
	}
}
