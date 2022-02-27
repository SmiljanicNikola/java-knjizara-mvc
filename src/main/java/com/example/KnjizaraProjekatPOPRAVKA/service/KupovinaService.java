package com.example.KnjizaraProjekatPOPRAVKA.service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.context.annotation.Lazy;

import com.example.KnjizaraProjekatPOPRAVKA.model.Kupovina;

@Lazy
public interface KupovinaService {

	Kupovina findOne(int id);
	
	Kupovina findOne(LocalDate datumKupovine);

	List<Kupovina> findAll();
	
	Kupovina deleteAll();
	
	Kupovina save(Kupovina kupovina);
	
	List<Kupovina> save(List<Kupovina> kupovine);
	
	Kupovina update(Kupovina kupovina);
	
	List<Kupovina> update(List<Kupovina> kupovine);
	
	Kupovina delete(Integer id);
	
	List<Kupovina> find(int id, int knjigaId, String musterijaOznaka);
	
	List<Kupovina> find(int knjigaId);
	
	List<Kupovina> find2(String musterijaOznaka);
	
	List<Kupovina> find2(LocalDate datumKupovineOd, LocalDate datumKupovineDo);
	
}
