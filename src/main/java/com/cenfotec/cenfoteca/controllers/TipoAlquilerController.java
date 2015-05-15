package com.cenfotec.cenfoteca.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cenfotec.cenfoteca.contracts.TipoAlquilerResponse;
import com.cenfotec.cenfoteca.ejb.TipoAlquiler;
import com.cenfotec.cenfoteca.pojo.TipoAlquilerPOJO;
import com.cenfotec.cenfoteca.services.GeneralServiceInterface;
import com.cenfotec.cenfoteca.utils.PojoUtils;


/**
 * Handles requests for the application home page.
 */
@RestController
@RequestMapping(value ="rest/protected/tipoAlquiler")
public class TipoAlquilerController {
	
	@Autowired
	GeneralServiceInterface generalService;
	
	@RequestMapping(value ="/getAll", method = RequestMethod.GET)
	public TipoAlquilerResponse getAll(){
		
		TipoAlquilerResponse tar = new TipoAlquilerResponse();
		
		List<TipoAlquiler> tipoAlquilerList = generalService.getAllTipoAlquiler();
		List<TipoAlquilerPOJO> tipoAlquilerViewList = new ArrayList<TipoAlquilerPOJO>();
		
		for (TipoAlquiler ta : tipoAlquilerList){
			TipoAlquilerPOJO ntipoAlquiler = new TipoAlquilerPOJO();
			PojoUtils.pojoMappingUtility(ntipoAlquiler,ta);
			tipoAlquilerViewList.add(ntipoAlquiler);
		}
		
		tar.setTipoAlquilerList(tipoAlquilerViewList);
		
		return tar;		
	}
}
