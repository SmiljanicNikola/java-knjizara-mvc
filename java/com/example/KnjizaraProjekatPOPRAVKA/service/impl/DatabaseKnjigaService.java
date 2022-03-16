package com.example.KnjizaraProjekatPOPRAVKA.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.KnjizaraProjekatPOPRAVKA.dao.KnjigaDAO;
import com.example.KnjizaraProjekatPOPRAVKA.model.Knjiga;
import com.example.KnjizaraProjekatPOPRAVKA.model.Zanr;
import com.example.KnjizaraProjekatPOPRAVKA.service.KnjigaService;

@Service
public class DatabaseKnjigaService implements KnjigaService {

	@Autowired
	private KnjigaDAO knjigaDAO;
	

	@Override
	public Knjiga findOne(int id) {
		return knjigaDAO.findOne(id);

	}

	@Override
	public Knjiga findOne(long isbn) {
		return null;
	}

	@Override
	public List<Knjiga> findAll() {
		return knjigaDAO.findAll();
	}

	@Override
	public Knjiga save(Knjiga knjiga) {
		knjigaDAO.save(knjiga);
		return knjiga;
	}

	@Override
	public List<Knjiga> save(List<Knjiga> knjige) {
		return null;
	
	}

	@Override
	public Knjiga update(Knjiga knjiga) {
		knjigaDAO.update(knjiga);
		return knjiga;
	}

	@Override
	public List<Knjiga> update(List<Knjiga> knjige) {
		return null;
	}

	@Override
	public Knjiga delete(int id) {
		Knjiga knjiga = findOne(id);
		if (knjiga != null) {
			knjigaDAO.delete(id);
		}
		return knjiga;
	}

	@Override
	public void delete(List<String> id) {
		// TODO Auto-generated method stub
	}

	@Override
	public List<Knjiga> find(int id, String naziv, Long isbn, String izdavackaKuca, String autor,
			LocalDate godinaIzdavanja, String kratakOpis, float cena, int brojStranica, String tipPoveza, String pismo,
			String jezik, float prosecnaOcena) {
				return null;
		
		/*List<Knjiga> knjige = knjigaDAO.findAll();
		
		if(naziv == null) {
			naziv="";
		}
		if(isbn == null){
			isbn = 0L;
		}
		if(izdavackaKuca == null) {
			izdavackaKuca="";
		}
		if(autor == null) {
			autor="";
		}
		if(godinaIzdavanja == null) {
			godinaIzdavanja = "0";
		}
		*/
		
		//return knjigaDAO.find(id, naziv, isbn, izdavackaKuca, autor, godinaIzdavanja, kratakOpis, cena, brojStranica, tipPoveza, pismo, jezik, prosecnaOcena);
	}

	@Override
	public List<Knjiga> findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Knjiga> findVerzija2(String naziv, Long isbn, String izdavackaKuca, String autor,
			LocalDate godinaIzdavanja, String kratakOpis, Float cena, Integer brojStranica, String tipPoveza, String pismo,
			String jezik, Float prosecnaOcena) {
		
		
		HashMap<String, Object> mapaArgumenata = new HashMap<String, Object>();
		
		if(naziv != null) {
			mapaArgumenata.put("naziv", naziv);
		}
		if(isbn != null) {
			mapaArgumenata.put("isbn", isbn);
		}
		if(izdavackaKuca != null) {
			mapaArgumenata.put("izdavackaKuca", izdavackaKuca);
		}
		if(autor != null) {
			mapaArgumenata.put("autor", autor);
		}
		if(godinaIzdavanja != null) {
			mapaArgumenata.put("godinaIzdavanja", godinaIzdavanja);
		}
		if(kratakOpis != null) {
			mapaArgumenata.put("kratakOpis", kratakOpis);
		}
		if(cena != null) {
			mapaArgumenata.put("cena", cena);
		}
		if(brojStranica != null) {
			mapaArgumenata.put("brojStranica", brojStranica);
		}
		if(tipPoveza != null) {
			mapaArgumenata.put("tipPoveza", tipPoveza);
		}
		if(pismo != null) {
			mapaArgumenata.put("pismo", pismo);
		}
		if(jezik != null) {
			mapaArgumenata.put("jezik", jezik);
		}
		if(prosecnaOcena != null) {
			mapaArgumenata.put("prosecnaOcena", prosecnaOcena);
		}
		return knjigaDAO.findVerzija2(mapaArgumenata);

		/*HashMap<String, Object> mapaArgumenata = new HashMap<String,Object>();
		
		if(naziv!=null) 
			mapaArgumenata.put("naziv", naziv);
		
		if(isbn!=null) 
			mapaArgumenata.put("isbn", isbn);
		
		if(izdavackaKuca!=null) 
			mapaArgumenata.put("izdavackaKuca", izdavackaKuca);
		
		if(autor!=null)
			mapaArgumenata.put("autor", autor);
		
		if(godinaIzdavanja!=null) 
			mapaArgumenata.put("godinaIzdavanja", godinaIzdavanja);
		
		if(kratakOpis!=null)
			mapaArgumenata.put("kratakOpis", kratakOpis);
		
		if(cena!=null) 
			mapaArgumenata.put("cena", cena);
		
		if(brojStranica!=null) 
			mapaArgumenata.put("brojStranica", brojStranica);
		
		if(tipPoveza!=null) 
			mapaArgumenata.put("tipPoveza", tipPoveza);
		if(pismo!=null) 
			
			mapaArgumenata.put("pismo", pismo);
		if(jezik!=null) 
			
			mapaArgumenata.put("jezik", jezik);
		
		if(prosecnaOcena!=null) 
			mapaArgumenata.put("prosecnaOcena", prosecnaOcena);
		
		
		return knjigaDAO.findVerzija2(mapaArgumenata);*/
	}

	@Override
	public Knjiga findOne(String naziv) {
		return knjigaDAO.findOne(naziv);

	}

	@Override
	public List<Knjiga> find(String naziv, Float cena, String autor, String jezik) {
		
		HashMap<String, Object> mapaArgumenata = new HashMap<String, Object>();
		
		if(naziv != null) {
			mapaArgumenata.put("naziv", naziv);
		}
		if(cena != null) {
			mapaArgumenata.put("cena", cena);
		}
		if(autor != null) {
			mapaArgumenata.put("autor", autor);
		}
		if(jezik != null) {
			mapaArgumenata.put("jezik", jezik);
		}
		return knjigaDAO.find(mapaArgumenata);
				
	}

	@Override
	public List<Knjiga> find2(String naziv, Long isbn, String izdavackaKuca, String autor, String kratakOpis, Float cenaOd, Float cenaDo, Integer brojStranica, String tipPoveza, String pismo,
			String jezik, Float prosecnaOcena,Integer brojPrimeraka, Integer zanrId) {
		
		List<Knjiga> knjige = knjigaDAO.findAll();
		
		if(naziv == null) {
			naziv = "";
		}
		if(isbn == null) {
			isbn = 0L;
		}
		if(izdavackaKuca == null) {
			izdavackaKuca = "";
		}
		if(autor == null) {
			autor = "";
		}
		
		
		if(kratakOpis == null) {
			kratakOpis = "";
		}
		
		if (cenaOd == null) {
			cenaOd = (float) 0;
		}
		if (cenaDo == null) {
			cenaDo = Float.MAX_VALUE;
		}
		if(brojStranica == null) {
			brojStranica = 0;
		}
		
		if(tipPoveza == null) {
			tipPoveza = "";
		}
		if(pismo == null) {
			pismo = "";
		}
		if(jezik == null) {
			jezik = "";
		}
		if(prosecnaOcena == null) {
			prosecnaOcena = (float) 0;
		}
		if (zanrId == null) {
			zanrId = 0;
		}

		List<Knjiga> rezultat = new ArrayList<>();
		for (Knjiga itKnjiga: knjige) {
			// kriterijum pretrage
			if (!itKnjiga.getNaziv().toLowerCase().contains(naziv.toLowerCase())) {
				continue;
			}
			if (!itKnjiga.getAutor().toLowerCase().contains(autor.toLowerCase())) {
				continue;
			}
			if (!itKnjiga.getIzdavackaKuca().toLowerCase().contains(izdavackaKuca.toLowerCase())) {
				continue;
			}
			if (!itKnjiga.getJezik().toLowerCase().contains(jezik.toLowerCase())) {
				continue;
			}
			if (!itKnjiga.getKratakOpis().toLowerCase().contains(kratakOpis.toLowerCase())) {
				continue;
			}
			
			if (zanrId > 0) { // ako je žanr odabran
				boolean pronadjen = false;
				for (Zanr itZanr: itKnjiga.getZanrovi()) {
					if (itZanr.getId() == zanrId) {
						pronadjen = true;
						break;
					}
				}
				if (!pronadjen) {
					continue;
				}
			}
			
			if (!(itKnjiga.getCena() >= cenaOd && itKnjiga.getCena() <= cenaDo)) {
				continue;
			}
			
			rezultat.add(itKnjiga);
			
		}

		return rezultat;
		
	}

	@Override
	public List<Knjiga> find(Integer[] ids) {
		List<Knjiga> rezultat = new ArrayList<>();
		for (Integer id: ids) {
			Knjiga knjiga = knjigaDAO.findOne(id);
			rezultat.add(knjiga);
		}

		return rezultat;
	}

	@Override
	public Knjiga update2(Knjiga knjiga) {
		knjigaDAO.update2(knjiga);
		return knjiga;
	}

	@Override
	public List<Knjiga> find3(String naziv, Long isbn, String izdavackaKuca, String autor, String kratakOpis,
			Float cena, Integer brojStranica, String tipPoveza, String pismo, String jezik, Float prosecnaOcena,
			Integer brojPrimeraka, Integer zanrId) {
		List<Knjiga> knjige = knjigaDAO.findAll();
		
		if(naziv == null) {
			naziv = "";
		}
		if(isbn == null) {
			isbn = 0L;
		}
		if(izdavackaKuca == null) {
			izdavackaKuca = "";
		}
		if(autor == null) {
			autor = "";
		}
		
		if(kratakOpis == null) {
			kratakOpis = "";
		}
		
		if (cena == null) {
			cena = (float) 0;
		}
		
		if(brojStranica == null) {
			brojStranica = 0;
		}
		
		if(tipPoveza == null) {
			tipPoveza = "";
		}
		if(pismo == null) {
			pismo = "";
		}
		if(jezik == null) {
			jezik = "";
		}
		if(prosecnaOcena == null) {
			prosecnaOcena = (float) 0;
		}
		if (zanrId == null) {
			zanrId = 0;
		}

		List<Knjiga> rezultat = new ArrayList<>();
		for (Knjiga itKnjiga: knjige) {
			// kriterijum pretrage
			if (!itKnjiga.getNaziv().toLowerCase().contains(naziv.toLowerCase())) {
				continue;
			}
			String stringIsbn=String.valueOf(itKnjiga.getIsbn());
			if (!stringIsbn.toLowerCase().contains(isbn.toString())) {
				continue;
			}
			/*if (!itKnjiga.getAutor().toLowerCase().contains(autor.toLowerCase())) {
				continue;
			}
			if (!itKnjiga.getIzdavackaKuca().toLowerCase().contains(izdavackaKuca.toLowerCase())) {
				continue;
			}
			if (!itKnjiga.getJezik().toLowerCase().contains(jezik.toLowerCase())) {
				continue;
			}
			if (!itKnjiga.getKratakOpis().toLowerCase().contains(kratakOpis.toLowerCase())) {
				continue;
			}*/
			/*String stringIsbn=String.valueOf(itKnjiga.getIsbn());
			if (!stringIsbn.toLowerCase().contains(isbn.toString())) {
				continue;
			}*/
			/*if (zanrId > 0) { // ako je žanr odabran
				boolean pronadjen = false;
				for (Zanr itZanr: itKnjiga.getZanrovi()) {
					if (itZanr.getId() == zanrId) {
						pronadjen = true;
						break;
					}
				}
				if (!pronadjen) {
					continue;
				}
			}*/
		
			rezultat.add(itKnjiga);	
		}

		return rezultat;
	}

}
