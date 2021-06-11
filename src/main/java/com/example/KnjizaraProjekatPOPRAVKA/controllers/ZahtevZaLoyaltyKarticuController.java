package com.example.KnjizaraProjekatPOPRAVKA.controllers;
import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.ModelAndView;

import com.example.KnjizaraProjekatPOPRAVKA.model.Korisnik;
import com.example.KnjizaraProjekatPOPRAVKA.model.LoyaltyKartica;
import com.example.KnjizaraProjekatPOPRAVKA.service.KorisnikService;
import com.example.KnjizaraProjekatPOPRAVKA.service.LoyaltyKarticaService;



@Controller
@RequestMapping(value="/ZahtevLoyaltyKartice")
public class ZahtevZaLoyaltyKarticuController implements ServletContextAware {

	public static final String KARTICA_KEY = "loyaltyKartica";
	
	
	@Autowired
	private LoyaltyKarticaService loyaltyKarticaService;

	@Autowired
	private KorisnikService korisnikService;
	
	@Autowired
	private ServletContext servletContext;
	private String baseURL;

	@PostConstruct
	public void init() {
		baseURL = servletContext.getContextPath() + "/";
	}
	
	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
		
	}
	
	@GetMapping
	public ModelAndView index(
			@RequestParam(required=false) Integer id,
			@RequestParam(required=false) String popust,
			@RequestParam(required=false) Integer brPoena,
			@RequestParam(required=false) String vlasnikOznaka,
			@RequestParam(required=false) String status,
			HttpSession session, HttpServletResponse response) throws IOException{
			
			Korisnik prijavljeniKorisnik = (Korisnik) session.getAttribute(KorisnikController.KORISNIK_KEY);
			
			ModelAndView rezultat = new ModelAndView("zahtevLoyaltyKartice");
			rezultat.addObject("prijavljeniKorisnik", prijavljeniKorisnik);
			
			return rezultat;
			
	}
	
	@GetMapping(value="/Create")
	public ModelAndView create(HttpSession session, HttpServletResponse response) throws IOException {
		Korisnik prijavljeniKorisnik  = (Korisnik) session.getAttribute(KorisnikController.KORISNIK_KEY);
		if(prijavljeniKorisnik == null){
			response.sendRedirect(baseURL + "index");
			return null;
		}
		//ModelAndView rezultat = new ModelAndView("dodavanjeKnjige");
		
		return null;
	}
	
	
	@PostMapping(value="/Create")
	public void create(@RequestParam(required=false) Integer id,
			@RequestParam (required=false) String popust,
			@RequestParam (required=false) Integer brPoena,
			@RequestParam String vlasnikOznaka,
			//(defaultValue="Na cekanju")
			@RequestParam String status,
			HttpSession session, HttpServletResponse response) throws IOException{
		//aut
		//Korisnik prijavljeniKorisnik = (Korisnik) session.getAttribute(KorisnikController.KORISNIK_KEY);
		/*if(prijavljeniKorisnik == null) {
			response.sendRedirect(baseURL+ "index");
			return;
			}*/
		
		//Korisnik prijavljeniKorisnik  = (Korisnik) session.getAttribute(KorisnikController.KORISNIK_KEY);
		/*if(prijavljeniKorisnik == null){
			response.sendRedirect(baseURL + "Knjige");
			return;
			}*/
		
		/*if(vlasnikOznaka != prijavljeniKorisnik.getKorisnickoIme()) {
			response.sendRedirect(baseURL);
			return;
			}
			
		session.setAttribute(KorisnikController.KORISNIK_KEY, prijavljeniKorisnik);	*/
		
		
		Korisnik vlasnik = korisnikService.findOne(vlasnikOznaka);
		if(vlasnik == null) {
			response.sendRedirect(baseURL);
			
		}
		
		LoyaltyKartica loyaltyKartica = new LoyaltyKartica(popust, brPoena, vlasnik, status);
		loyaltyKarticaService.save(loyaltyKartica);
		
		response.sendRedirect(baseURL);
		

		}
}
