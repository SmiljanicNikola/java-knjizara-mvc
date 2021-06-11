package com.example.KnjizaraProjekatPOPRAVKA.model;
public class KorisnickaKorpa {

	private int id;
	private Knjiga knjiga;
	private Integer kolicina;
	private Korisnik vlasnikKorpe;
	
	public KorisnickaKorpa() {
		
	}

	
	

	public KorisnickaKorpa(int id, Knjiga knjiga, Integer kolicina, Korisnik vlasnikKorpe) {
		super();
		this.id = id;
		this.knjiga = knjiga;
		this.kolicina = kolicina;
		this.vlasnikKorpe = vlasnikKorpe;
	}

	public KorisnickaKorpa(Knjiga knjiga, Integer kolicina, Korisnik vlasnikKorpe) {
		super();
		this.knjiga = knjiga;
		this.kolicina = kolicina;
		this.vlasnikKorpe = vlasnikKorpe;
	}


	public Integer getKolicina() {
		return kolicina;
	}


	public void setKolicina(Integer kolicina) {
		this.kolicina = kolicina;
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




	public Korisnik getVlasnikKorpe() {
		return vlasnikKorpe;
	}




	public void setVlasnikKorpe(Korisnik vlasnikKorpe) {
		this.vlasnikKorpe = vlasnikKorpe;
	}




	@Override
	public String toString() {
		return "KorisnickaKorpa [id=" + id + ", knjiga=" + knjiga + ", kolicina=" + kolicina + ", vlasnikKorpe="
				+ vlasnikKorpe + "]";
	}

	
	
	
	
}
