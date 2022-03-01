package com.example.KnjizaraProjekatPOPRAVKA.model;


public class ListaZelja {

	private int id;
	
	private Knjiga knjiga;
	
	private Korisnik vlasnikListe;
	
	
	public ListaZelja() {
		
	}
	

	public ListaZelja(int id, Knjiga knjiga, Korisnik vlasnikListe) {
		super();
		this.id = id;
		this.knjiga = knjiga;
		this.vlasnikListe = vlasnikListe;
	}
	

	public ListaZelja(Knjiga knjiga, Korisnik vlasnikListe) {
		super();
		this.knjiga = knjiga;
		this.vlasnikListe = vlasnikListe;
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

	public Korisnik getVlasnikListe() {
		return vlasnikListe;
	}

	public void setVlasnikListe(Korisnik vlasnikListe) {
		this.vlasnikListe = vlasnikListe;
	}

	@Override
	public String toString() {
		return "ListaZelja [id=" + id + ", knjiga=" + knjiga + ", vlasnikListe=" + vlasnikListe + "]";
	}
		
}
