package com.cenfotec.cenfoteca.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cenfotec.cenfoteca.contracts.UsersRequest;
import com.cenfotec.cenfoteca.contracts.UsersResponse;
import com.cenfotec.cenfoteca.ejb.TipoUsuario;
import com.cenfotec.cenfoteca.ejb.Usuario;
import com.cenfotec.cenfoteca.pojo.UsuarioPOJO;
import com.cenfotec.cenfoteca.services.GeneralServiceInterface;
import com.cenfotec.cenfoteca.services.RentServiceInterface;
import com.cenfotec.cenfoteca.services.UsersServiceInterface;
import com.cenfotec.cenfoteca.utils.PojoUtils;


/**
 * Handles requests for the application home page.
 */
@RestController
@RequestMapping(value ="rest/protected/users")
public class UsersController {
	
	@Autowired
	UsersServiceInterface usersService;
	
	@Autowired
	GeneralServiceInterface generalService;
	
	@Autowired
	RentServiceInterface rentService;
	
	@Autowired
	HttpServletRequest request;
	
	@RequestMapping(value ="/getAll", method = RequestMethod.POST)
	@Transactional
	public UsersResponse getAll(@RequestBody UsersRequest ur){	
		
		ur.setPageNumber(ur.getPageNumber() - 1);
		Page<Usuario> users = usersService.getAll(ur);
		
		UsersResponse us = new UsersResponse();
		
		us.setCode(200);
		us.setCodeMessage("users fetch success");
		us.setTotalElements(users.getTotalElements());
		us.setTotalPages(users.getTotalPages());
		
		
		List<UsuarioPOJO> viewUsers = new ArrayList<UsuarioPOJO>();
		
		users.getContent().forEach(u->{
			UsuarioPOJO nuser = new UsuarioPOJO();
			nuser.setEmail(u.getEmail());
			nuser.setFirstname(u.getFirstname());
			nuser.setIdTipoUsuario(u.getTipoUsuario().getIdTipoUsuario());
			nuser.setIdUsuario(u.getIdUsuario());
			nuser.setLastname(u.getLastname());
			viewUsers.add(nuser);
		});
		
		us.setUsuarios(viewUsers);
		return us;		
	}
	
	@RequestMapping(value ="/create", method = RequestMethod.POST)
	public UsersResponse create(@RequestBody UsersRequest ur){	
		
		UsersResponse us = new UsersResponse();
		TipoUsuario tp = generalService.getTipoUsuarioById(ur.getUser().getIdTipoUsuario());
		
		Usuario user = new Usuario();
		user.setFirstname(ur.getUser().getFirstname());
		user.setLastname(ur.getUser().getLastname());
		user.setEmail(ur.getUser().getEmail());
		user.setPassword("resetPasswordTodo");
		user.setTipoUsuario(tp);
		
		Boolean state = usersService.saveUser(user);
		if(state){
			us.setCode(200);
			us.setCodeMessage("user created succesfully");
		}
		return us;
		
	}
}
