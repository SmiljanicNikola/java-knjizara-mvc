package com.example.KnjizaraProjekatPOPRAVKA.service;

import java.time.LocalDate;
import java.util.List;

import com.example.KnjizaraProjekatPOPRAVKA.model.Komentar;
import com.example.KnjizaraProjekatPOPRAVKA.model.KorisnickaKorpa;

public interface KorisnickaKorpaService {

	KorisnickaKorpa findOne(int id);
	
	List<KorisnickaKorpa> findAll();
	
	int deleteAll();
	
	KorisnickaKorpa save(KorisnickaKorpa korisnickaKorpa);
	
	List<KorisnickaKorpa> save(List<KorisnickaKorpa> korisnickeKorpe);
	
	KorisnickaKorpa update(KorisnickaKorpa korisnickaKorpa);
	
	List<KorisnickaKorpa> update(List<KorisnickaKorpa> korisnickeKorpe);
	
	KorisnickaKorpa delete(Integer id);
	
	List<Komentar> find(int id, int knjigaId);
	
	List<KorisnickaKorpa> find(int knjigaId);
	
	
	
}
