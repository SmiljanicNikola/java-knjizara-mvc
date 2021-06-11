package com.example.KnjizaraProjekatPOPRAVKA.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.KnjizaraProjekatPOPRAVKA.dao.KorisnikDAO;
import com.example.KnjizaraProjekatPOPRAVKA.model.Korisnik;
import com.example.KnjizaraProjekatPOPRAVKA.service.KorisnikService;


@Service
public class DatabaseKorisnikService implements KorisnikService {

	@Autowired
	private KorisnikDAO korisnikDAO;
	
	
	
	@Override
	public Korisnik findOne(String korisnickoIme) {
		return korisnikDAO.findOne(korisnickoIme);
	}

	@Override
	public Korisnik findOne(String korisnickoIme, String lozinka) {
		return korisnikDAO.findOne(korisnickoIme, lozinka);
	}

	@Override
	public List<Korisnik> findAll() {
		return korisnikDAO.findAll();
	}

	@Override
	public Korisnik save(Korisnik korisnik) {
		korisnikDAO.save(korisnik);
		return korisnik;
	}

	@Override
	public List<Korisnik> save(List<Korisnik> korisnici) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Korisnik update(Korisnik korisnik) {
		korisnikDAO.update(korisnik);
		return korisnik;
	}

	@Override
	public List<Korisnik> update(List<Korisnik> korisnici) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Korisnik delete(String korisnickoIme) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(List<String> korisnickoIme) {
		// TODO Auto-generated method stub
		
	}

	//@Override
	/*public List<Korisnik> find(String ime, String prezime, String email, String adresa, String telefon,
			String korisnickoIme, LocalDate datumRodjenja, LocalDateTime datumRegistracije, String uloga) {
		return korisnikDAO.find(ime, prezime, email, adresa,telefon,korisnickoIme,
				datumRodjenja, datumRegistracije, uloga);
	}*/

	@Override
	public List<Korisnik> findByKorisnickoIme(String korisnickoIme) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Korisnik> find(String ime, String prezime, String email, String adresa, String telefon,
			String korisnickoIme, String lozinka, LocalDate datumRodjenja, LocalDateTime datumRegistracije,
			String uloga) {
		return korisnikDAO.find(ime, prezime, email, adresa,telefon,korisnickoIme, lozinka,
				datumRodjenja, datumRegistracije, uloga);
	}

}
