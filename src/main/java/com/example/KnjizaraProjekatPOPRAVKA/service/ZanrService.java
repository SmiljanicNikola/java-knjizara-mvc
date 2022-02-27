package com.example.KnjizaraProjekatPOPRAVKA.service;

import java.util.List;

import com.example.KnjizaraProjekatPOPRAVKA.model.Zanr;

public interface ZanrService {

	Zanr findOne(Integer id);
	
	List<Zanr> findAll();
	
	List<Zanr> find(Integer[] ids);
	
	Zanr save(Zanr zanr);
	
	List<Zanr> save(List<Zanr> zanrovi);
	
	Zanr update(Zanr zanr);
	
	List<Zanr> update(List<Zanr> zanrovi);
	
	Zanr delete(Integer id);
	
	//void delete(List<Long> ids);
	
	List<Zanr> find(String ime);
	
}
