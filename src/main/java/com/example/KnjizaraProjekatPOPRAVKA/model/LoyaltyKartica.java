package com.example.KnjizaraProjekatPOPRAVKA.model;


public class LoyaltyKartica {
	
	private int id;
	
	private int popust;
	
	private int brPoena;
	
	private Korisnik vlasnik;
	
	private String Status;

	
	public LoyaltyKartica() {}


	public LoyaltyKartica(int popust, int brPoena, Korisnik vlasnik, String status) {
		super();
		this.popust = popust;
		this.brPoena = brPoena;
		this.vlasnik = vlasnik;
		Status = status;
	}


	public LoyaltyKartica(int id, int popust, int brPoena, Korisnik vlasnik, String status) {
		super();
		this.id = id;
		this.popust = popust;
		this.brPoena = brPoena;
		this.vlasnik = vlasnik;
		Status = status;
	}
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPopust() {
		return popust;
	}

	public void setPopust(int popust) {
		this.popust = popust;
	}

	public int getBrPoena() {
		return brPoena;
	}

	public void setBrPoena(int brPoena) {
		this.brPoena = brPoena;
	}

	public Korisnik getVlasnik() {
		return vlasnik;
	}

	public void setVlasnik(Korisnik vlasnik) {
		this.vlasnik = vlasnik;
	}


	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	@Override
	public String toString() {
		return "LoyaltyKartica [id=" + id + ", popust=" + popust + ", brPoena=" + brPoena + ", vlasnik=" + vlasnik
				+ ", Status=" + Status + "]";
	}
	
	
}
