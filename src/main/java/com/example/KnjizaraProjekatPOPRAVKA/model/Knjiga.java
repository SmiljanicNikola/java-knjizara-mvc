package com.example.KnjizaraProjekatPOPRAVKA.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class Knjiga {
	
	private int id;
	private String naziv;
	private long isbn;
	private String izdavackaKuca;
	private String autor;
	private LocalDate godinaIzdavanja;
	private String kratakOpis;
	private float cena;
	private int brojStranica;
	private String tipPoveza="tvrdi";
	private String pismo="latinica";
	private String jezik;
	private float prosecnaOcena;
	private int brojPrimeraka;
	
	private List<Zanr> zanrovi = new ArrayList<>();

	
	public Knjiga() {}

	public Knjiga(int id, String naziv, long isbn, String izdavackaKuca, String autor, LocalDate godinaIzdavanja,
			String kratakOpis, float cena, int brojStranica, String tipPoveza, String pismo, String jezik,
			float prosecnaOcena) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.isbn = isbn;
		this.izdavackaKuca = izdavackaKuca;
		this.autor = autor;
		this.godinaIzdavanja = godinaIzdavanja;
		this.kratakOpis = kratakOpis;
		this.cena = cena;
		this.brojStranica = brojStranica;
		this.tipPoveza = tipPoveza;
		this.pismo = pismo;
		this.jezik = jezik;
		this.prosecnaOcena = prosecnaOcena;
	}
	
	public Knjiga(String naziv, long isbn, String izdavackaKuca, String autor, LocalDate godinaIzdavanja,
			String kratakOpis, float cena, int brojStranica, String tipPoveza, String pismo, String jezik,
			float prosecnaOcena) {
		super();
		this.naziv = naziv;
		this.isbn = isbn;
		this.izdavackaKuca = izdavackaKuca;
		this.autor = autor;
		this.godinaIzdavanja = godinaIzdavanja;
		this.kratakOpis = kratakOpis;
		this.cena = cena;
		this.brojStranica = brojStranica;
		this.tipPoveza = tipPoveza;
		this.pismo = pismo;
		this.jezik = jezik;
		this.prosecnaOcena = prosecnaOcena;
	}

	
	
	
	
	public Knjiga(int id, String naziv, long isbn, String izdavackaKuca, String autor, LocalDate godinaIzdavanja,
			String kratakOpis, float cena, int brojStranica, String tipPoveza, String pismo, String jezik,
			float prosecnaOcena, int brojPrimeraka) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.isbn = isbn;
		this.izdavackaKuca = izdavackaKuca;
		this.autor = autor;
		this.godinaIzdavanja = godinaIzdavanja;
		this.kratakOpis = kratakOpis;
		this.cena = cena;
		this.brojStranica = brojStranica;
		this.tipPoveza = tipPoveza;
		this.pismo = pismo;
		this.jezik = jezik;
		this.prosecnaOcena = prosecnaOcena;
		this.brojPrimeraka = brojPrimeraka;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public long getIsbn() {
		return isbn;
	}

	public void setIsbn(long isbn) {
		this.isbn = isbn;
	}

	public String getIzdavackaKuca() {
		return izdavackaKuca;
	}

	public void setIzdavackaKuca(String izdavackaKuca) {
		this.izdavackaKuca = izdavackaKuca;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public LocalDate getGodinaIzdavanja() {
		return godinaIzdavanja;
	}

	public void setGodinaIzdavanja(LocalDate godinaIzdavanja) {
		this.godinaIzdavanja = godinaIzdavanja;
	}

	public String getKratakOpis() {
		return kratakOpis;
	}

	public void setKratakOpis(String kratakOpis) {
		this.kratakOpis = kratakOpis;
	}


	public int getBrojStranica() {
		return brojStranica;
	}

	public void setBrojStranica(int brojStranica) {
		this.brojStranica = brojStranica;
	}

	public String getTipPoveza() {
		return tipPoveza;
	}

	public void setTipPoveza(String tipPoveza) {
		this.tipPoveza = tipPoveza;
	}

	public String getPismo() {
		return pismo;
	}

	public void setPismo(String pismo) {
		this.pismo = pismo;
	}

	public String getJezik() {
		return jezik;
	}

	public void setJezik(String jezik) {
		this.jezik = jezik;
	}

	


	public int getBrojPrimeraka() {
		return brojPrimeraka;
	}

	public void setBrojPrimeraka(int brojPrimeraka) {
		this.brojPrimeraka = brojPrimeraka;
	}

	public float getCena() {
		return cena;
	}

	public void setCena(float cena) {
		this.cena = cena;
	}

	public float getProsecnaOcena() {
		return prosecnaOcena;
	}

	public void setProsecnaOcena(float prosecnaOcena) {
		this.prosecnaOcena = prosecnaOcena;
	}
	
	
	public List<Zanr> getZanrovi() {
		return zanrovi;
	}

	public void setZanrovi(List<Zanr> zanrovi) {
		//this.zanrovi = zanrovi;
		this.zanrovi.clear();
		this.zanrovi.addAll(zanrovi);
	}

	@Override
	public String toString() {
		return "Knjiga [id=" + id + ", naziv=" + naziv + ", isbn=" + isbn + ", izdavackaKuca=" + izdavackaKuca
				+ ", autor=" + autor + ", godinaIzdavanja=" + godinaIzdavanja + ", kratakOpis=" + kratakOpis + ", cena="
				+ cena + ", brojStranica=" + brojStranica + ", tipPoveza=" + tipPoveza + ", pismo=" + pismo + ", jezik="
				+ jezik + ", prosecnaOcena=" + prosecnaOcena + "]";
	}
	
	
	
	
	
}
