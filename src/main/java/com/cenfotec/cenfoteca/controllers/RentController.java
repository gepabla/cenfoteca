package com.cenfotec.cenfoteca.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cenfotec.cenfoteca.contracts.RentItemsResponse;
import com.cenfotec.cenfoteca.contracts.RentResponse;
import com.cenfotec.cenfoteca.ejb.Alquiler;
import com.cenfotec.cenfoteca.ejb.Usuario;
import com.cenfotec.cenfoteca.pojo.AlquilerPOJO;
import com.cenfotec.cenfoteca.services.GeneralServiceInterface;
import com.cenfotec.cenfoteca.services.RentServiceInterface;
import com.cenfotec.cenfoteca.services.UsersServiceInterface;
import com.cenfotec.cenfoteca.utils.PojoUtils;
import com.cenfotec.cenfoteca.utils.Utils;


/**
 * Handles requests for the application home page.
 */
@RestController
@RequestMapping(value ="rest/protected/rent")
public class RentController {
	
	@Autowired
	ServletContext servletContext;
	
	@Autowired
	GeneralServiceInterface generalService;
	
	@Autowired
	RentServiceInterface rentService;
	
	@Autowired
	UsersServiceInterface usersService;
	
	@Autowired
	HttpServletRequest request;
	
	@RequestMapping(value ="/getAll", method = RequestMethod.GET)
	@Transactional
	public RentItemsResponse getAll(){
		
		RentItemsResponse ris = new RentItemsResponse();
		
		HttpSession currentSession = request.getSession();
		int idUser = (int) currentSession.getAttribute("idUser");
		Usuario user = usersService.getSessionUser(idUser);
		
		List<Alquiler> list = user.getAlquilers();
		List<Integer> rentIds = new ArrayList<Integer>();
		
		for (Alquiler alq : list){
			rentIds.add(alq.getIdAlquiler());
		}
		
		List<Alquiler> noRentList = new ArrayList<Alquiler>();
		if(rentIds.size() ==  0){
			noRentList = rentService.getAll();
		}else{
			noRentList = rentService.getNoUserRentList(rentIds);
		}
		
		
		System.out.println(list.size());
		System.out.println(noRentList.size());
		
		List<AlquilerPOJO> viewList = new ArrayList<AlquilerPOJO>();
		for (Alquiler origin : noRentList){
			AlquilerPOJO target = new AlquilerPOJO();
			PojoUtils.pojoMappingUtility(target,origin);
			viewList.add(target);
		}
		
		ris.setAlquileres(viewList);
		ris.setCode(200);
		
		return ris;
		
		
	}
	
	@RequestMapping(value ="/getUserAll", method = RequestMethod.GET)
	@Transactional
	public RentItemsResponse getUserAll(){
		
		RentItemsResponse ris = new RentItemsResponse();
		
		HttpSession currentSession = request.getSession();
		int idUser = (int) currentSession.getAttribute("idUser");
		Usuario user = usersService.getSessionUser(idUser);
		
		List<Alquiler> list = user.getAlquilers();
	
		List<AlquilerPOJO> viewList = new ArrayList<AlquilerPOJO>();
		for (Alquiler origin : list){
			AlquilerPOJO target = new AlquilerPOJO();
			PojoUtils.pojoMappingUtility(target,origin);
			viewList.add(target);
		}
		
		ris.setAlquileres(viewList);
		ris.setCode(200);
		
		return ris;
		
	}
	
	@RequestMapping(value ="/rent", method = RequestMethod.GET)
	@Transactional
	public RentItemsResponse rent(@RequestParam("id") int idAlquiler){
		RentItemsResponse rr = new RentItemsResponse();
		
		Alquiler alquiler = rentService.getAlquiler(idAlquiler);
		
		HttpSession currentSession = request.getSession();
		int idUser = (int) currentSession.getAttribute("idUser");
		Usuario user = usersService.getSessionUser(idUser);
		
		List<Alquiler> list = user.getAlquilers();
		list.add(alquiler);
		user.setAlquilers(list);
		Boolean state = usersService.saveUser(user);
		
		if(state){
			rr.setCode(200);
			rr.setCodeMessage("rent item succesfully");

		}else{
			rr.setCode(409);
			rr.setErrorMessage("rent item with errors");
		}
		return rr;
	}
	
	@RequestMapping(value ="/create", method = RequestMethod.POST)
	public RentResponse create(
			@RequestParam("file") MultipartFile file,
			@RequestParam("idTipoAlquiler") int idTipoAlquiler,
			@RequestParam("name") String name,
			@RequestParam("description") String description){	
		
		RentResponse rs = new RentResponse();
		String resultFileName = Utils.writeToFile(file,servletContext);
		if(!resultFileName.equals("")){
			
			Alquiler alquiler = new Alquiler();
			alquiler.setName(name);
			alquiler.setDescription(description);
			alquiler.setImage(resultFileName);
			alquiler.setTipoAlquiler(generalService.getTipoAlquilerById(idTipoAlquiler));
			
			Boolean state = rentService.saveRent(alquiler);
			
			if(state){
				rs.setCode(200);
				rs.setCodeMessage("rent created succesfully");
			}
			
		}else{
			//create a common webservice error codes enum or statics
			rs.setCode(409);
			rs.setErrorMessage("create/edit conflict");
		}
	
		return rs;		
	}
	
}
