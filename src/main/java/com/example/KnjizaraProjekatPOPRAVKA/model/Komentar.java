package com.example.KnjizaraProjekatPOPRAVKA.model;

import java.time.LocalDate;

public class Komentar {

	private int id;
	private String tekstKomentara;
	private int ocena;
	private LocalDate datumPostavljanja;
	private Korisnik autor;
	private Knjiga knjiga;
	private String Status="Na cekanju";
	
	
	public Komentar() {}
	
	
	
	
	public Komentar(int id, String tekstKomentara, int ocena, LocalDate datumPostavljanja, Korisnik autor,
			Knjiga knjiga, String status) {
		super();
		this.id = id;
		this.tekstKomentara = tekstKomentara;
		this.ocena = ocena;
		this.datumPostavljanja = datumPostavljanja;
		this.autor = autor;
		this.knjiga = knjiga;
		Status = status;
	}
	
	




	public Komentar(String tekstKomentara, int ocena, LocalDate datumPostavljanja, Korisnik autor, Knjiga knjiga,
			String status) {
		super();
		this.tekstKomentara = tekstKomentara;
		this.ocena = ocena;
		this.datumPostavljanja = datumPostavljanja;
		this.autor = autor;
		this.knjiga = knjiga;
		Status = status;
	}




	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTekstKomentara() {
		return tekstKomentara;
	}
	public void setTekstKomentara(String tekstKomentara) {
		this.tekstKomentara = tekstKomentara;
	}
	public int getOcena() {
		return ocena;
	}
	public void setOcena(int ocena) {
		this.ocena = ocena;
	}
	
	
	
	
	public LocalDate getDatumPostavljanja() {
		return datumPostavljanja;
	}




	public void setDatumPostavljanja(LocalDate datumPostavljanja) {
		this.datumPostavljanja = datumPostavljanja;
	}




	public Korisnik getAutor() {
		return autor;
	}
	public void setAutor(Korisnik autor) {
		this.autor = autor;
	}
	public Knjiga getKnjiga() {
		return knjiga;
	}
	public void setKnjiga(Knjiga knjiga) {
		this.knjiga = knjiga;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	
	
	@Override
	public String toString() {
		return "Komentar [id=" + id + ", tekstKomentara=" + tekstKomentara + ", ocena=" + ocena + ", datumPostavljanja="
				+ datumPostavljanja + ", autor=" + autor + ", knjiga=" + knjiga + ", Status=" + Status + "]";
	}
	
	
	
	
	
}
