package com.example.KnjizaraProjekatPOPRAVKA.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class Kupovina {

	private int id;
	private Knjiga knjiga;
	private Float ukupnaCena;
	private LocalDate datumKupovine;
	private Korisnik musterija;
	private int brojKupljenihKnjiga;
	
	public Kupovina() {}

	public Kupovina(Float ukupnaCena, LocalDate datumKupovine, Korisnik musterija, int brojKupljenihKnjiga) {
		super();
		this.ukupnaCena = ukupnaCena;
		this.datumKupovine = datumKupovine;
		this.musterija = musterija;
		this.brojKupljenihKnjiga = brojKupljenihKnjiga;
	}


	public Kupovina(Knjiga knjiga, Float ukupnaCena, LocalDate datumKupovine, Korisnik musterija,
			int brojKupljenihKnjiga) {
		super();
		this.knjiga = knjiga;
		this.ukupnaCena = ukupnaCena;
		this.datumKupovine = datumKupovine;
		this.musterija = musterija;
		this.brojKupljenihKnjiga = brojKupljenihKnjiga;
	}


	public Kupovina(int id, Knjiga knjiga, Float ukupnaCena, LocalDate datumKupovine, Korisnik musterija,
			int brojKupljenihKnjiga) {
		super();
		this.id = id;
		this.knjiga = knjiga;
		this.ukupnaCena = ukupnaCena;
		this.datumKupovine = datumKupovine;
		this.musterija = musterija;
		this.brojKupljenihKnjiga = brojKupljenihKnjiga;
	}

	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	
	
	public Knjiga getKnjiga() {
		return knjiga;
	}



	public void setKnjiga(Knjiga knjiga) {
		this.knjiga = knjiga;
	}



	public Float getUkupnaCena() {
		return ukupnaCena;
	}


	public void setUkupnaCena(Float ukupnaCena) {
		this.ukupnaCena = ukupnaCena;
	}





	public LocalDate getDatumKupovine() {
		return datumKupovine;
	}
	
	public void setDatumKupovine(LocalDate datumKupovine) {
		this.datumKupovine = datumKupovine;
	}
	
	public Korisnik getMusterija() {
		return musterija;
	}
	
	public void setMusterija(Korisnik musterija) {
		this.musterija = musterija;
	}
	
	public int getBrojKupljenihKnjiga() {
		return brojKupljenihKnjiga;
	}
	
	public void setBrojKupljenihKnjiga(int brojKupljenihKnjiga) {
		this.brojKupljenihKnjiga = brojKupljenihKnjiga;
	}



	@Override
	public String toString() {
		return "Kupovina [id=" + id + ", knjiga=" + knjiga + ", ukupnaCena=" + ukupnaCena + ", datumKupovine="
				+ datumKupovine + ", musterija=" + musterija + ", brojKupljenihKnjiga=" + brojKupljenihKnjiga + "]";
	}


	
	
	
	
	
	
}
