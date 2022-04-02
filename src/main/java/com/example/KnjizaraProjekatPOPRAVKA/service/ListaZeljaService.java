package com.example.KnjizaraProjekatPOPRAVKA.service;

import java.util.List;

import com.example.KnjizaraProjekatPOPRAVKA.model.KorisnickaKorpa;
import com.example.KnjizaraProjekatPOPRAVKA.model.ListaZelja;


public interface ListaZeljaService {

	ListaZelja findOne(int id);
	
	List<ListaZelja> findAll();
	
	KorisnickaKorpa deleteAll();
	
	ListaZelja save(ListaZelja listaZelja);
	
	List<ListaZelja> save(List<ListaZelja> listeZelja);
	
	ListaZelja update(ListaZelja listaZelja);
	
	List<ListaZelja> update(List<ListaZelja> listeZelja);
	
	ListaZelja delete(Integer id);
	
	List<ListaZelja> find(int id, int knjigaId, int vlasnikListeId);
	
	List<ListaZelja> find(int knjigaId);
	
	List<ListaZelja> find2(String vlasnikListeOznaka);
	
	
}
