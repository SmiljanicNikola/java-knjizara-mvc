package com.example.KnjizaraProjekatPOPRAVKA.dao;


import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

import com.example.KnjizaraProjekatPOPRAVKA.model.Komentar;


public interface KomentarDAO {
	
	
	public Komentar findOne(int id);
	
	public List<Komentar> findAll();
	
	public List<Komentar> find(int id, String tekstKomentara, int ocena, LocalDate datumPostavljanja,
			String autorOznaka, int knjigaId, String status);
	
	public List<Komentar> find(int knjigaId);
	
	public List<Komentar> findVerzija2(HashMap<String, Object> mapaArgumenata);
	
	public void save(Komentar komentar);
	
	public void update(Komentar komentar);
	
	public int delete(int id);

}
