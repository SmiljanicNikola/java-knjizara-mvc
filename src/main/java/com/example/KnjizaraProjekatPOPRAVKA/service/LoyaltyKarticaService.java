package com.example.KnjizaraProjekatPOPRAVKA.service;

import java.time.LocalDate;
import java.util.List;

import com.example.KnjizaraProjekatPOPRAVKA.model.LoyaltyKartica;

public interface LoyaltyKarticaService {
	LoyaltyKartica findOne(int id);
	
	LoyaltyKartica findOne(String vlasnikOznaka);
	
	List<LoyaltyKartica> findAll();
	
	LoyaltyKartica save(LoyaltyKartica loyaltyKartica);
	
	List<LoyaltyKartica> save(List<LoyaltyKartica> loyaltyKartice);
	
	LoyaltyKartica update(LoyaltyKartica loyaltyKartica);
	
	List<LoyaltyKartica> update(List<LoyaltyKartica> loyaltyKartice);
	
	LoyaltyKartica delete(int id);
	
	void delete(List<String> id);
	
	List<LoyaltyKartica> find(int id, String popust, int brPoena,
			String vlasnikOznaka, String status);
	
	List<LoyaltyKartica> findById(int id); 
}
