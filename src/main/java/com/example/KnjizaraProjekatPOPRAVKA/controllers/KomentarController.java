package com.example.KnjizaraProjekatPOPRAVKA.controllers;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

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
import com.example.KnjizaraProjekatPOPRAVKA.model.Komentar;
import com.example.KnjizaraProjekatPOPRAVKA.model.Korisnik;
import com.example.KnjizaraProjekatPOPRAVKA.service.KnjigaService;
import com.example.KnjizaraProjekatPOPRAVKA.service.KomentarService;
import com.example.KnjizaraProjekatPOPRAVKA.service.KorisnikService;


@Controller
@RequestMapping(value="/Komentari")
public class KomentarController implements ServletContextAware{

	public static final String KOMENTAR_KEY = "komentar";
	
	@Autowired
	private KomentarService komentarService;
	
	@Autowired
	private KorisnikService korisnikService;
	
	@Autowired
	private KnjigaService knjigaService;
	
	@Autowired
	private ServletContext servletContext;
	private String baseURL;

	@Autowired
	private LocaleResolver localeResolver;
	
	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
		
	}

	@PostConstruct
	public void init() {
		baseURL = servletContext.getContextPath() + "/";
	}
	
	@GetMapping
	public ModelAndView index(
			@RequestParam(required=false) Integer id,
			@RequestParam(required=false) String tekstKomentara,
			@RequestParam(required=false) Integer ocena,
			@RequestParam(required=false) @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) LocalDate datumPostavljanja,
			@RequestParam(required=false) String autorOznaka,
			@RequestParam(required=false) Integer knjigaId,
			@RequestParam(required=false) String status,
			HttpSession session) throws IOException{
		
			
			List<Komentar> komentari = komentarService.findAll();
			
			ModelAndView rezultat = new ModelAndView("komentari");
			rezultat.addObject("komentari", komentari);
			
			return rezultat;
		
			}
	
			
	@GetMapping(value="/Create")
	public ModelAndView create(HttpSession session, HttpServletResponse response) throws IOException{
		Korisnik prijavljeniKorisnik = (Korisnik) session.getAttribute(KorisnikController.KORISNIK_KEY);
		if(prijavljeniKorisnik == null) {
			response.sendRedirect(baseURL + "knjige");
			return null;
		}
		
		return null;
	}
	
	
	@GetMapping("menjajLokalizacijuNaSrpski")
	public void index2(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		System.out.println("postavljanje lokalizacije za sesiju iz kontrolera na sr");
		localeResolver.setLocale(request, response, Locale.forLanguageTag("sr"));
		
		response.sendRedirect(baseURL+"Komentari");
	}
	
	
	@GetMapping("menjajLokalizacijuNaEngleski")
	public void index3(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		System.out.println("postavljanje lokalizacije za sesiju iz kontrolera na en");
		localeResolver.setLocale(request, response, Locale.forLanguageTag("en"));
		
		response.sendRedirect(baseURL+"Komentari");
	}	
	
	
	@PostMapping(value="/Create")
	public void create(@RequestParam(required=false)Integer id,
			@RequestParam String tekstKomentara,
			@RequestParam Integer ocena,
			//@RequestParam @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) LocalDate datumPostavljanja,
			@RequestParam String autorOznaka,
			@RequestParam Integer knjigaId,
			@RequestParam(defaultValue="Na cekanju") String status,
			HttpSession session, HttpServletResponse response) throws IOException{
		
		Korisnik prijavljeniKorisnik = (Korisnik) session.getAttribute(KorisnikController.KORISNIK_KEY);
		if(prijavljeniKorisnik == null) {
			response.sendRedirect(baseURL + "Knjige");
			return;
		}
		Korisnik autor = korisnikService.findOne(autorOznaka);
		if(autor == null) {
			response.sendRedirect(baseURL+ "Knjige");
			return;
		}
		Knjiga knjiga = knjigaService.findOne(knjigaId);
		if(knjiga == null) {
			response.sendRedirect(baseURL+ "Knjige");
			return;
		}
		
		Komentar komentar = new Komentar(tekstKomentara, ocena, LocalDate.now(), autor, knjiga, status);
		komentarService.save(komentar);
		
		response.sendRedirect(baseURL+ "Knjige");
		
	}
	
			
	@GetMapping(value = "/Details")
	public ModelAndView details(@RequestParam (required=false) Integer id, HttpSession session, HttpServletRequest request,
			HttpServletResponse response) throws IOException{
		Komentar komentar = komentarService.findOne(id);
		
		ModelAndView rezultat = new ModelAndView("komentar");
		rezultat.addObject("komentar", komentar);
		
		return rezultat;
	}
	
	
	@PostMapping(value="/Edit")
	public void edit(@RequestParam Integer id,
					@RequestParam String tekstKomentara,
					@RequestParam Integer ocena,
					@RequestParam @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) LocalDate datumPostavljanja,
					@RequestParam String autorOznaka,
					@RequestParam Integer knjigaId,
					@RequestParam String status,
					HttpSession session, HttpServletResponse response) throws IOException{
		
					Korisnik prijavljeniKorisnik = (Korisnik) session.getAttribute(KorisnikController.KORISNIK_KEY);
					
					if(prijavljeniKorisnik == null) {
						response.sendRedirect(baseURL + "Komentari");
						return;
					}
						Komentar komentar = komentarService.findOne(id);
						if(komentar == null) {
							response.sendRedirect(baseURL + "Komentari");
						}
						
						Korisnik autor = korisnikService.findOne(autorOznaka);
						if (autor == null) {
							response.sendRedirect(baseURL + "Komentari");
							return;
						}
						
						Knjiga knjiga = knjigaService.findOne(knjigaId);
						if(knjiga == null) {
							response.sendRedirect(baseURL + "Knjige");
							return;
						}
						
						komentar.setId(id);
						komentar.setTekstKomentara(tekstKomentara);
						komentar.setOcena(ocena);
						komentar.setDatumPostavljanja(LocalDate.now());
						komentar.setAutor(autor);
						komentar.setKnjiga(knjiga);
						komentar.setStatus(status);
						
						komentarService.update(komentar);
						response.sendRedirect(baseURL+"Komentari");
						
			}

}
			
			
			
		
	
	
	
	
	
	

