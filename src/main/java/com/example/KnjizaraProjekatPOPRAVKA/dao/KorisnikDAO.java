package com.example.KnjizaraProjekatPOPRAVKA.dao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.example.KnjizaraProjekatPOPRAVKA.model.Korisnik;


public interface KorisnikDAO {
	
	
	public Korisnik findOne(String korisnickoIme);
	
	
	public Korisnik findOne(String korisnickoIme, String lozinka);
	
	
	public List<Korisnik> findAll();
	
	
	public List<Korisnik> find(String ime, String prezime, String email, String adresa, String telefon, String korisnickoIme, String lozinka,
			LocalDate datumRodjenja, LocalDateTime datumRegistracije, String uloga);
	
	
	public void save(Korisnik korisnik);
	
	
	public void update(Korisnik korisnik);
	
	
	public void delete(String korisnickoIme);
	
}
