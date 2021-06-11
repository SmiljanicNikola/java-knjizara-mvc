package com.example.KnjizaraProjekatPOPRAVKA.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.KnjizaraProjekatPOPRAVKA.dao.ListaZeljaDAO;
import com.example.KnjizaraProjekatPOPRAVKA.model.KorisnickaKorpa;
import com.example.KnjizaraProjekatPOPRAVKA.model.ListaZelja;
import com.example.KnjizaraProjekatPOPRAVKA.service.ListaZeljaService;


@Service
public class DatabaseListaZeljaService implements ListaZeljaService {

	
	@Autowired
	private ListaZeljaDAO listaZeljaDAO;
	
	@Override
	public ListaZelja findOne(int id) {
		return listaZeljaDAO.findOne(id);
	}

	@Override
	public List<ListaZelja> findAll() {
		return listaZeljaDAO.findAll();
	}

	@Override
	public KorisnickaKorpa deleteAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ListaZelja save(ListaZelja listaZelja) {
		listaZeljaDAO.save(listaZelja);
		return listaZelja;
	}

	@Override
	public List<ListaZelja> save(List<ListaZelja> listeZelja) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ListaZelja update(ListaZelja listaZelja) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ListaZelja> update(List<ListaZelja> listeZelja) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ListaZelja delete(Integer id) {
		ListaZelja listaZelja = findOne(id);
		if (listaZelja != null) {
			listaZeljaDAO.delete(id);
		}
		return listaZelja;
	}

	@Override
	public List<ListaZelja> find(int id, int knjigaId, int vlasnikListeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ListaZelja> find(int knjigaId) {
		// TODO Auto-generated method stub
		return null;
	}

	

	@Override
	public List<ListaZelja> find2(String vlasnikListeOznaka) {
		// TODO Auto-generated method stub
		return null;
	}

}
