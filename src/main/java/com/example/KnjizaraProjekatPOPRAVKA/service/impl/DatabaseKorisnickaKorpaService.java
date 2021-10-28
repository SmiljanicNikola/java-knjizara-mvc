package com.example.KnjizaraProjekatPOPRAVKA.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.KnjizaraProjekatPOPRAVKA.dao.KorisnickaKorpaDAO;
import com.example.KnjizaraProjekatPOPRAVKA.model.Komentar;
import com.example.KnjizaraProjekatPOPRAVKA.model.KorisnickaKorpa;
import com.example.KnjizaraProjekatPOPRAVKA.service.KorisnickaKorpaService;


@Service
public class DatabaseKorisnickaKorpaService implements KorisnickaKorpaService {

	@Autowired
	private KorisnickaKorpaDAO korisnickaKorpaDAO;
	
	@Override
	public KorisnickaKorpa findOne(int id) {
		return korisnickaKorpaDAO.findOne(id);
	}

	@Override
	public List<KorisnickaKorpa> findAll() {
		return korisnickaKorpaDAO.findAll();
	}

	
	
	@Override
	public KorisnickaKorpa save(KorisnickaKorpa korisnickaKorpa) {
		korisnickaKorpaDAO.save(korisnickaKorpa);
		return korisnickaKorpa;
	}

	@Override
	public List<KorisnickaKorpa> save(List<KorisnickaKorpa> korisnickeKorpe) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public KorisnickaKorpa update(KorisnickaKorpa korisnickaKorpa) {
		korisnickaKorpaDAO.update(korisnickaKorpa);
		return korisnickaKorpa;
	}

	@Override
	public List<KorisnickaKorpa> update(List<KorisnickaKorpa> korisnickeKorpe) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public KorisnickaKorpa delete(Integer id) {
		KorisnickaKorpa korisnickaKorpa = findOne(id);
		if (korisnickaKorpa != null) {
			korisnickaKorpaDAO.delete(id);
		}
		return korisnickaKorpa;
	}

	@Override
	public List<Komentar> find(int id, int knjigaId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<KorisnickaKorpa> find(int knjigaId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteAll() {
		return korisnickaKorpaDAO.deleteAll();
	}

	

	

}
