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
import com.example.KnjizaraProjekatPOPRAVKA.model.KorisnickaKorpa;
import com.example.KnjizaraProjekatPOPRAVKA.model.Korisnik;
import com.example.KnjizaraProjekatPOPRAVKA.service.KnjigaService;
import com.example.KnjizaraProjekatPOPRAVKA.service.KorisnickaKorpaService;
import com.example.KnjizaraProjekatPOPRAVKA.service.KorisnikService;


@Controller
@RequestMapping(value="/KorisnickaKorpa")
public class KorisnickaKorpaController implements ServletContextAware{
	
	public static final String KORPA_KEY = "korisnickakorpa";
	
	@Autowired
	private KnjigaService knjigaService;
	
	@Autowired
	private KorisnikService korisnikService;
	
	@Autowired
	private LocaleResolver localeResolver;
	
	@Autowired
	private KorisnickaKorpaService korisnickaKorpaService;
	
	@Autowired
	private ServletContext servletContext;
	private String baseURL;
	
	
	@Override
	public void setServletContext(ServletContext servletContext) {
		// TODO Auto-generated method stub
		
	}
	
	
	@PostConstruct
	public void init() {
		baseURL = servletContext.getContextPath() + "/";
	}
	
	
	@GetMapping
	public ModelAndView index(
			@RequestParam(required=false) Integer knjigaId,
			@RequestParam(required=false) Integer kolicina,
			@RequestParam(required=false) String vlasnikKorpeOznaka,
			HttpSession session
			)throws IOException{
				List<KorisnickaKorpa> stavke = korisnickaKorpaService.findAll();
				
				ModelAndView rezultat = new ModelAndView("korisnickaKorpa");
				rezultat.addObject("stavke", stavke);
				
				return rezultat;
		
	}
	

	@GetMapping(value="/Create")
	public ModelAndView create(HttpSession session, HttpServletResponse response) throws IOException{
		Korisnik prijavljeniKorisnik = (Korisnik) session.getAttribute(KorisnikController.KORISNIK_KEY);
		if(prijavljeniKorisnik == null) {
			response.sendRedirect(baseURL);
			return null;
		}
		
		return null;
	}
	
	
	@PostMapping(value="/Create")
	public void create(@RequestParam(required=false)Integer knjigaId,
			@RequestParam(required=false)Integer kolicina,
			HttpSession session, HttpServletResponse response) throws IOException{
		
		Korisnik prijavljeniKorisnik = (Korisnik) session.getAttribute(KorisnikController.KORISNIK_KEY);
		if(prijavljeniKorisnik == null) {
			response.sendRedirect(baseURL + "Knjige");
			return;
		}
		
		Knjiga knjiga = knjigaService.findOne(knjigaId);
		if(knjiga == null) {
			response.sendRedirect(baseURL);
			return;
		}
		
		Korisnik vlasnikKorpe = korisnikService.findOne(prijavljeniKorisnik.getKorisnickoIme());
		if(vlasnikKorpe == null) {
			response.sendRedirect(baseURL);
			return;
		}
		
		KorisnickaKorpa korisnickaKorpa = new KorisnickaKorpa(knjiga,kolicina,vlasnikKorpe);
		korisnickaKorpaService.save(korisnickaKorpa);
		
		response.sendRedirect(baseURL + "Knjige");
		
	}
	
	
	@GetMapping(value="/Delete")
	public void delete(@RequestParam int id,
			HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		Korisnik prijavljeniKorisnik = (Korisnik) session.getAttribute(KorisnikController.KORISNIK_KEY);
		if (prijavljeniKorisnik == null) {
			response.sendRedirect(baseURL + "Knjige");
			return;
		}
		
		korisnickaKorpaService.delete(id);
		
		response.sendRedirect(baseURL + "KorisnickaKorpa");
		
	}
	
	
	@GetMapping(value="/Details")
	public ModelAndView details(@RequestParam (required=false) Integer id,@RequestParam (required=false) String naziv, HttpSession session, HttpServletRequest request,
				HttpServletResponse response)  {
					
					KorisnickaKorpa stavkaKorpe = korisnickaKorpaService.findOne(id);
					
					
						
					ModelAndView rezultat = new ModelAndView("stavkaKorpe");
					rezultat.addObject("stavkaKorpe", stavkaKorpe);
		
					
					return rezultat;
				}
	
	
	@GetMapping("menjajLokalizacijuNaSrpski")
	public void index2(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		System.out.println("postavljanje lokalizacije za sesiju iz kontrolera na sr");
		localeResolver.setLocale(request, response, Locale.forLanguageTag("sr"));
		
		response.sendRedirect(baseURL+"KorisnickaKorpa");
	}	
	
	
	@GetMapping("menjajLokalizacijuNaEngleski")
	public void index3(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		System.out.println("postavljanje lokalizacije za sesiju iz kontrolera na en");
		localeResolver.setLocale(request, response, Locale.forLanguageTag("en"));
		
		response.sendRedirect(baseURL+"KorisnickaKorpa");
	}	
	
	
}
