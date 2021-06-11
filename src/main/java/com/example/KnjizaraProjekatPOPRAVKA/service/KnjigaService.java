package com.example.KnjizaraProjekatPOPRAVKA.service;


import java.time.LocalDate;
import java.util.List;

import com.example.KnjizaraProjekatPOPRAVKA.model.Knjiga;



public interface KnjigaService {

	Knjiga findOne(int id);
	Knjiga findOne(String naziv);
	Knjiga findOne(long isbn);
	List<Knjiga> findAll();
	Knjiga save(Knjiga knjiga);
	List<Knjiga> save(List<Knjiga> knjige);
	Knjiga update(Knjiga knjiga);
	List<Knjiga> update(List<Knjiga> knjige);
	Knjiga delete(int id);
	void delete(List<String> id);
	List<Knjiga> find(int id, String naziv, Long isbn, String izdavackaKuca, String autor, LocalDate godinaIzdavanja,
			String kratakOpis, float cena, int brojStranica, String tipPoveza, String pismo, String jezik, float prosecnaOcena);
	
	List<Knjiga> find2(String naziv, Long isbn, String izdavackaKuca, String autor,
			String kratakOpis, Float cenaOd, Float cenaDo, Integer brojStranica, String tipPoveza, String pismo, String jezik, Float prosecnaOcena,Integer brojPrimeraka, Integer zanrId);
	
    List<Knjiga> findVerzija2(String naziv, Long isbn, String izdavackaKuca, String autor, LocalDate godinaIzdavanja,
			String kratakOpis, Float cena, Integer brojStranica, String tipPoveza, String pismo, String jezik, Float prosecnaOcena);
	
	List<Knjiga> findById(int id); 
	
	List<Knjiga> find(String naziv, Float cena, String autor, String jezik);
	
	List<Knjiga> find(Integer[] ids);
	Knjiga update2(Knjiga knjiga);

	List<Knjiga> find3(String naziv, Long isbn, String izdavackaKuca, String autor,
			String kratakOpis, Float cena, Integer brojStranica, String tipPoveza, String pismo, String jezik, Float prosecnaOcena,Integer brojPrimeraka, Integer zanrId);
}
