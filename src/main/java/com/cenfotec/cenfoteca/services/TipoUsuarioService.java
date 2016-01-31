package com.cenfotec.cenfoteca.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cenfotec.cenfoteca.contracts.TipoUsuarioRequest;
import com.cenfotec.cenfoteca.ejb.TipoUsuario;
import com.cenfotec.cenfoteca.pojo.TipoUsuarioPOJO;
import com.cenfotec.cenfoteca.repositories.TipoUsuarioRepository;

@Service
public class TipoUsuarioService implements TipoUsuarioServiceInterface {
	@Autowired private TipoUsuarioRepository tipoUsuarioRepository;
	
	@Override
	@Transactional
	public List<TipoUsuarioPOJO> getAll() {
		List<TipoUsuario> tipos = tipoUsuarioRepository.findAll();
		List<TipoUsuarioPOJO> dtos = new ArrayList<TipoUsuarioPOJO>();
		tipos.stream().forEach(ta ->{
			TipoUsuarioPOJO dto = new TipoUsuarioPOJO();
			BeanUtils.copyProperties(ta, dto);
			dtos.add(dto);
		});
		return dtos;
	}

	@Override
	public TipoUsuario getTipoUsuarioById(int idTipoUsuario) {
		return tipoUsuarioRepository.findOne(idTipoUsuario);
	}

	@Override
	@Transactional
	public Boolean saveTipoUsuario(TipoUsuario tUsuario) {
		
		BeanUtils.copyProperties(tUsuario.getTipo(), tUsuario);
		
		TipoUsuario nTUsuario = tipoUsuarioRepository.save(tUsuario);
		
		return (nTUsuario == null) ? false : true;
	}

}
