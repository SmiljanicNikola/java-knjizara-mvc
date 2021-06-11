package com.example.KnjizaraProjekatPOPRAVKA.dao;


import java.util.HashMap;
import java.util.List;

import com.example.KnjizaraProjekatPOPRAVKA.model.ListaZelja;
import com.example.KnjizaraProjekatPOPRAVKA.model.LoyaltyKartica;


public interface LoyaltyKarticaDAO {

	public LoyaltyKartica findOne(int id);
	
	public LoyaltyKartica findOne(String vlasnikOznaka);
	
	public List<LoyaltyKartica> findAll();
	
	public List<LoyaltyKartica> find(int id, String popust, int brPoena,
			String vlasnikOznaka, String status);
	

	public List<LoyaltyKartica> findVerzija2(HashMap<String, Object> mapaArgumenata);

	
	
	public void save(LoyaltyKartica loyaltyKartica);
	
	public void update(LoyaltyKartica loyaltyKartica);
	
	public int delete(int id);
	
}
