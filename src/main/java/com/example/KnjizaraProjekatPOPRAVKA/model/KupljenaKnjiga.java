package com.example.KnjizaraProjekatPOPRAVKA.model;


public class KupljenaKnjiga {

	private int id;
	
	private Knjiga knjiga;
	
	private int brojPrimeraka;
	
	private int cena;
	
	
	public KupljenaKnjiga() {}


	public KupljenaKnjiga(int id, Knjiga knjiga, int brojPrimeraka, int cena) {
		super();
		this.id = id;
		this.knjiga = knjiga;
		this.brojPrimeraka = brojPrimeraka;
		this.cena = cena;
	}


	public KupljenaKnjiga(Knjiga knjiga, int brojPrimeraka, int cena) {
		super();
		this.knjiga = knjiga;
		this.brojPrimeraka = brojPrimeraka;
		this.cena = cena;
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


	public int getBrojPrimeraka() {
		return brojPrimeraka;
	}


	public void setBrojPrimeraka(int brojPrimeraka) {
		this.brojPrimeraka = brojPrimeraka;
	}

	
	public int getCena() {
		return cena;
	}


	public void setCena(int cena) {
		this.cena = cena;
	}


	@Override
	public String toString() {
		return "KupljenaKnjiga [id=" + id + ", knjiga=" + knjiga + ", brojPrimeraka=" + brojPrimeraka + ", cena=" + cena
				+ "]";
	}
	
	
}
