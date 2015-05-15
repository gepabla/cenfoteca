package com.cenfotec.cenfoteca.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cenfotec.cenfoteca.ejb.Alquiler;
import com.cenfotec.cenfoteca.repositories.RentRepository;

@Service
public class RentService implements RentServiceInterface{

	@Autowired
	RentRepository rentRepository;
	
	@Override
	public Boolean saveRent(Alquiler alquiler) {
		Alquiler nalquiler = rentRepository.save(alquiler);
		
		Boolean result = true;
		if(nalquiler == null){
			result = false;
		}
		return result;
		
	}

	@Override
	public List<Alquiler> getNoUserRentList(List<Integer> list) {
		return rentRepository.findByIdAlquilerNotIn(list);
	}

	@Override
	public Alquiler getAlquiler(int idAlquiler) {
		return rentRepository.findOne(idAlquiler);
	}

	@Override
	public List<Alquiler> getAll() {
		return (List<Alquiler>) rentRepository.findAll();
	}
}