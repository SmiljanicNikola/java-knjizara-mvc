package com.example.KnjizaraProjekatPOPRAVKA.controllers;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;

import com.example.KnjizaraProjekatPOPRAVKA.model.Knjiga;
import com.example.KnjizaraProjekatPOPRAVKA.model.KorisnickaKorpa;
import com.example.KnjizaraProjekatPOPRAVKA.model.Korisnik;
import com.example.KnjizaraProjekatPOPRAVKA.model.Kupovina;
import com.example.KnjizaraProjekatPOPRAVKA.model.LoyaltyKartica;
import com.example.KnjizaraProjekatPOPRAVKA.service.KnjigaService;
import com.example.KnjizaraProjekatPOPRAVKA.service.KorisnickaKorpaService;
import com.example.KnjizaraProjekatPOPRAVKA.service.KorisnikService;
import com.example.KnjizaraProjekatPOPRAVKA.service.KupovinaService;
import com.example.KnjizaraProjekatPOPRAVKA.service.LoyaltyKarticaService;


@Controller
@RequestMapping(value="/Kupovine")
public class KupovinaController implements ServletContextAware {

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;		
	}
	
	@Autowired
	private LocaleResolver localeResolver;
	
	@Autowired
	private ServletContext servletContext;
	private String baseURL;
	
	@PostConstruct
	public void init() {
		baseURL = servletContext.getContextPath() + "/";
	}
	
	@Autowired
	private KupovinaService kupovinaService;
	
	@Autowired
	private KorisnickaKorpaService korisnickaKorpaService;
	
	@Autowired
	private LoyaltyKarticaService LoyaltyKarticaService;
	
	@Autowired
	private KnjigaService knjigaService;
	
	@Autowired
	private KorisnikService korisnikService;
	
	
	/*@GetMapping
	public ModelAndView index(@RequestParam(required=false) Integer id,
							 @RequestParam(required=false) Strin

							 HttpSession session) throws IOException{
												
							
							
							//List<Knjiga> knjige = knjigaService.find(id, naziv, isbn, izdavackaKuca, autor, godinaIzdavanja, kratakOpis, cena, brojStranica, tipPoveza, pismo, jezik, prosecnaOcena);
							//List<Knjiga> knjige = knjigaService.findAll();
							List<Kupovina> kupovine = kupovinaService.findAll();

													
							ModelAndView rezultat = new ModelAndView("kupovine");
							rezultat.addObject("kupovine", kupovine);
							
							
							return rezultat;			
	}*/
	
	/*@GetMapping(value="/Create")
	public ModelAndView create(HttpSession session, HttpServletResponse response) throws IOException {
		Korisnik prijavljeniKorisnik  = (Korisnik) session.getAttribute(KorisnikController.KORISNIK_KEY);
		if(prijavljeniKorisnik == null){
			response.sendRedirect(baseURL);
			return null;
		}
		List<com.ftn.KnjizaraProjekat.model.Zanr> zanrovi = zanrService.findAll();

		// prosleđivanje
		ModelAndView rezultat = new ModelAndView("dodavanjeKnjige");
		rezultat.addObject("zanrovi", zanrovi);

		return rezultat;
	}*/
	@GetMapping(value="/Create")
	public ModelAndView create(HttpSession session, HttpServletResponse response) throws IOException {
		Korisnik prijavljeniKorisnik  = (Korisnik) session.getAttribute(KorisnikController.KORISNIK_KEY);
		if(prijavljeniKorisnik == null){
			response.sendRedirect(baseURL + "Knjige");
			return null;
		}
		List<Knjiga> knjige = knjigaService.findAll();
		List<KorisnickaKorpa> stavka = korisnickaKorpaService.findAll();

		// prosleđivanje
		ModelAndView rezultat = new ModelAndView("stavkaKorpe");
		rezultat.addObject("knjige", knjige);

		return rezultat;
	}
	
	@PostMapping(value="/Create")
	public void create(@RequestParam(required=false) Integer id,
			//@RequestParam Integer knjigaId,
			@RequestParam Integer knjigaId,
			@RequestParam Float ukupnaCena,
			@RequestParam @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) LocalDate datumKupovine, 
			@RequestParam String musterijaOznaka,
			@RequestParam Integer brojKupljenihKnjiga,
			HttpSession session, HttpServletResponse response) throws IOException {
		
		Korisnik prijavljeniKorisnik  = (Korisnik) session.getAttribute(KorisnikController.KORISNIK_KEY);
		if(prijavljeniKorisnik == null){
			response.sendRedirect(baseURL + "Knjige");
			return;
		}
		
		Knjiga knjiga = knjigaService.findOne(knjigaId);
		if(knjiga == null) {
			response.sendRedirect(baseURL + "KorisnickaKorpa");
			return;
		}
		
		Korisnik musterija = korisnikService.findOne(musterijaOznaka);
		if(musterija == null) {
			response.sendRedirect(baseURL+ "KorisnickaKorpa");
			return;
		}

		Kupovina kupovina = new Kupovina(knjiga,ukupnaCena,datumKupovine,musterija,brojKupljenihKnjiga);
		
		LoyaltyKartica kartica = LoyaltyKarticaService.findOne(musterijaOznaka);
		if(kartica != null) {
			Integer brojBodova = (int)(ukupnaCena/1000);
			Integer stariBrojBodova = kartica.getBrPoena();
			kartica.setBrPoena(brojBodova+stariBrojBodova);
			
			
			Integer dobijeniPopust = brojBodova *5;
			Integer stariPopust = kartica.getPopust();
			kartica.setPopust(dobijeniPopust+stariPopust);
			LoyaltyKarticaService.update(kartica);
		}
		
		knjiga.setBrojPrimeraka(knjiga.getBrojPrimeraka()-brojKupljenihKnjiga);
		knjigaService.update2(knjiga);
		
		kupovinaService.save(kupovina);
		korisnickaKorpaService.delete(knjigaId);
		
		response.sendRedirect(baseURL + "KorisnickaKorpa");
	}
	
	@GetMapping(value="/Details")
	public ModelAndView details(@RequestParam @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) LocalDate datumKupovine, HttpSession session, HttpServletRequest request,
				HttpServletResponse response) throws IOException {
					
					Kupovina kupovina = kupovinaService.findOne(datumKupovine);
					
					ModelAndView rezultat = new ModelAndView("kupovina");
					
					rezultat.addObject("kupovina", kupovina);


					
					return rezultat;
				}
	
	

}
