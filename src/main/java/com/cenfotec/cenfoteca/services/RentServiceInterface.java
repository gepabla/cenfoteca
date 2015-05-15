package com.cenfotec.cenfoteca.services;

import java.util.List;

import com.cenfotec.cenfoteca.ejb.Alquiler;


public interface RentServiceInterface {

	Boolean saveRent(Alquiler alquiler);

	List<Alquiler> getNoUserRentList(List<Integer> rentIds);

	Alquiler getAlquiler(int idAlquiler);

	List<Alquiler> getAll();
}
