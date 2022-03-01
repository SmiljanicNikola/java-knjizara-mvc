package com.example.KnjizaraProjekatPOPRAVKA.model;
import java.time.LocalDate;
import java.time.LocalDateTime;


public class Korisnik {
	
	private String ime;
	
	private String prezime;
	
	private String email;
	
	private String adresa;
	
	private String telefon;
	
	private String korisnickoIme;
	
	private String lozinka;
	
	private LocalDate datumRodjenja;
	
	private LocalDateTime datumRegistracije;
	
	private String uloga="kupac";
	
	private boolean prijavljen = false;
	
	private boolean blokiran = false;

	
	public Korisnik() {}


	public Korisnik(String ime, String prezime, String email, String adresa, String telefon, String korisnickoIme,
			String lozinka, LocalDate datumRodjenja, LocalDateTime datumRegistracije, String uloga,
			boolean prijavljen) {
		super();
		this.ime = ime;
		this.prezime = prezime;
		this.email = email;
		this.adresa = adresa;
		this.telefon = telefon;
		this.korisnickoIme = korisnickoIme;
		this.lozinka = lozinka;
		this.datumRodjenja = datumRodjenja;
		this.datumRegistracije = datumRegistracije;
		this.uloga = uloga;
		this.prijavljen = prijavljen;
	}
	
	
	public Korisnik(String ime, String prezime, String email, String adresa, String telefon, String korisnickoIme,
			String lozinka, LocalDate datumRodjenja, String uloga,
			boolean prijavljen) {
		super();
		this.ime = ime;
		this.prezime = prezime;
		this.email = email;
		this.adresa = adresa;
		this.telefon = telefon;
		this.korisnickoIme = korisnickoIme;
		this.lozinka = lozinka;
		this.datumRodjenja = datumRodjenja;
		this.uloga = uloga;
		this.prijavljen = prijavljen;
	}
	

	public Korisnik(String ime, String prezime, String email, String adresa, String telefon, String korisnickoIme,
			String lozinka, LocalDate datumRodjenja, LocalDateTime datumRegistracije, String uloga, boolean prijavljen,
			boolean blokiran) {
		super();
		this.ime = ime;
		this.prezime = prezime;
		this.email = email;
		this.adresa = adresa;
		this.telefon = telefon;
		this.korisnickoIme = korisnickoIme;
		this.lozinka = lozinka;
		this.datumRodjenja = datumRodjenja;
		this.datumRegistracije = datumRegistracije;
		this.uloga = uloga;
		this.prijavljen = prijavljen;
		this.blokiran = blokiran;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime*result + ((korisnickoIme == null) ? 0 : korisnickoIme.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Korisnik other = (Korisnik) obj;
		if (korisnickoIme == null) {
			if (other.korisnickoIme != null)
				return false;
		} else if (!korisnickoIme.equals(other.korisnickoIme))
			return false;
		return true;
	}
	

	public Korisnik(String ime, String prezime, String email, String adresa, String telefon, String korisnickoIme,
			String lozinka, LocalDate datumRodjenja, LocalDateTime datumRegistracije, String uloga) {
		super();
		this.ime = ime;
		this.prezime = prezime;
		this.email = email;
		this.adresa = adresa;
		this.telefon = telefon;
		this.korisnickoIme = korisnickoIme;
		this.lozinka = lozinka;
		this.datumRodjenja = datumRodjenja;
		this.datumRegistracije = datumRegistracije;
		this.uloga = uloga;
	}


	public String getIme() {
		return ime;
	}
	public void setIme(String ime) {
		this.ime = ime;
	}
	public String getPrezime() {
		return prezime;
	}
	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAdresa() {
		return adresa;
	}
	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}
	public String getTelefon() {
		return telefon;
	}
	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}
	public String getKorisnickoIme() {
		return korisnickoIme;
	}
	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}
	public String getLozinka() {
		return lozinka;
	}
	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}
	public LocalDate getDatumRodjenja() {
		return datumRodjenja;
	}
	public void setDatumRodjenja(LocalDate datumRodjenja) {
		this.datumRodjenja = datumRodjenja;
	}
	public LocalDateTime getDatumRegistracije() {
		return datumRegistracije;
	}
	public void setDatumRegistracije(LocalDateTime datumRegistracije) {
		this.datumRegistracije = datumRegistracije;
	}
	public String getUloga() {
		return uloga;
	}
	public void setUloga(String uloga) {
		this.uloga = uloga;
	}
	public boolean isPrijavljen() {
		return prijavljen;
	}
	public void setPrijavljen(boolean prijavljen) {
		this.prijavljen = prijavljen;
	}
	public boolean isBlokiran() {
		return blokiran;
	}
	public void setBlokiran(boolean blokiran) {
		this.blokiran = blokiran;
	}

	@Override
	public String toString() {
		return "Korisnik [ime=" + ime + ", prezime=" + prezime + ", email=" + email + ", adresa=" + adresa
				+ ", telefon=" + telefon + ", korisnickoIme=" + korisnickoIme + ", lozinka=" + lozinka
				+ ", datumRodjenja=" + datumRodjenja + ", datumRegistracije=" + datumRegistracije + ", uloga=" + uloga
				+ "]";
	}

	
}
