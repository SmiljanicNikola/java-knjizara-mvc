package com.example.KnjizaraProjekatPOPRAVKA.service;

import java.time.LocalDate;
import java.util.List;

import com.example.KnjizaraProjekatPOPRAVKA.model.Komentar;



public interface KomentarService {
	
	Komentar findOne(int id);
	List<Komentar> findAll();
	Komentar save(Komentar komentar);
	List<Komentar> save(List<Komentar> komentari);
	Komentar update(Komentar komentar);
	List<Komentar> update(List<Komentar> komentari);
	Komentar delete(int id);
	void delete(List<String> id);
	List<Komentar> find(int id, String tekstKomentara, int ocena, LocalDate datumPostavljanja,
			String autorOznaka, int knjigaId, String status);
	
	List<Komentar> find(int knjigaId);
	
    //List<Knjiga> findVerzija2(int id, String naziv, Long isbn, String izdavackaKuca, String autor, LocalDate godinaIzdavanja,
		//	String kratakOpis, Float cena, Integer brojStranica, String tipPoveza, String pismo, String jezik, Float prosecnaOcena);
	
	List<Komentar> findById(int id); 
	
}
