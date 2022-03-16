package com.example.KnjizaraProjekatPOPRAVKA.dao;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

import com.example.KnjizaraProjekatPOPRAVKA.model.Kupovina;


public interface KupovinaDAO {
	
	
	public Kupovina findOne(int id);
	
	
	public Kupovina findOne(LocalDate datumKupovine);

	
	public List<Kupovina> findAll();
		
	
	public List<Kupovina> find(int id, String musterijaOznaka);
	
	
	public List<Kupovina> find(int knjigaId);
	
	
	public List<Kupovina> find(String musterijaOznaka);

	
	public void save(Kupovina kupovina);
	
	
	public int update(Kupovina kupovina);
	
	
	public void delete(Integer id);
	
	
	public List<Kupovina> find(HashMap<String, Object> mapaArgumenata);
	
}
