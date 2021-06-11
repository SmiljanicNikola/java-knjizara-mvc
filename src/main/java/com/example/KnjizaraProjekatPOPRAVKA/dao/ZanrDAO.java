package com.example.KnjizaraProjekatPOPRAVKA.dao;


import java.util.ArrayList;
import java.util.List;

import com.example.KnjizaraProjekatPOPRAVKA.model.Zanr;

public interface ZanrDAO {

	public Zanr findOne(Integer id);

	public List<Zanr> findAll();

	public List<Zanr> find(String ime);
	
	public int save(Zanr zanr);

	public int [] save(ArrayList<Zanr> zanrovi);
	
	public int update(Zanr zanr);

	public int delete(Integer id);
	
}
