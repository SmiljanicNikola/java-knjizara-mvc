package com.example.KnjizaraProjekatPOPRAVKA.controllers;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletContext;
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
import org.springframework.web.servlet.ModelAndView;

import com.example.KnjizaraProjekatPOPRAVKA.model.Knjiga;
import com.example.KnjizaraProjekatPOPRAVKA.model.Komentar;
import com.example.KnjizaraProjekatPOPRAVKA.model.Korisnik;
import com.example.KnjizaraProjekatPOPRAVKA.service.KnjigaService;
import com.example.KnjizaraProjekatPOPRAVKA.service.KomentarService;
import com.example.KnjizaraProjekatPOPRAVKA.service.KorisnikService;



@Controller
@RequestMapping(value="/DodavanjeKomentara")
public class DodavanjeKomentaraController implements ServletContextAware {

	public static final String KOMENTAR_KEY = "komentar";//Test

	@Autowired
	private KomentarService komentarService;
	
	@Autowired
	private KorisnikService korisnikService;
	
	@Autowired
	private KnjigaService knjigaService;
	
	@Autowired
	private ServletContext servletContext;
	private String baseURL;
	
	@Override
	public void setServletContext(ServletContext servletContext) {
		// TODO Auto-generated method stub
		
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
		
			
			Korisnik prijavljeniKorisnik = (Korisnik) session.getAttribute(KorisnikController.KORISNIK_KEY);
			//Knjiga izabranaKnjiga = knjigaService.findOne(knjigaId);
			
			ModelAndView rezultat = new ModelAndView("dodavanjeKomentara");
			rezultat.addObject("prijavljeniKorisnik", prijavljeniKorisnik);
			//rezultat.addObject("izabranaKnjiga", izabranaKnjiga);
		
			return rezultat;
		
			}
	
	@GetMapping(value="/Create")
	public String create(HttpSession session, HttpServletResponse response) throws IOException{
		Korisnik prijavljeniKorisnik = (Korisnik) session.getAttribute(KorisnikController.KORISNIK_KEY);
		if(prijavljeniKorisnik == null) {
			response.sendRedirect(baseURL + "index");
			return null;
		}
		
		response.sendRedirect("baseURL");
		return null;
	}
	
	
	@PostMapping(value="/Create")
	public void create(@RequestParam(required=false)Integer id,
			@RequestParam String tekstKomentara,
			@RequestParam Integer ocena,
			@RequestParam @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) LocalDate datumPostavljanja,
			@RequestParam String autorOznaka,
			@RequestParam int knjigaId,
			@RequestParam(defaultValue="Na cekanju") String status,
			HttpSession session, HttpServletResponse response) throws IOException{
		
		Korisnik prijavljeniKorisnik = (Korisnik) session.getAttribute(KorisnikController.KORISNIK_KEY);
		if(prijavljeniKorisnik == null) {
			response.sendRedirect(baseURL);
			return;
		}
		Korisnik autor = korisnikService.findOne(autorOznaka);
		if(autor == null) {
			response.sendRedirect(baseURL);
		}
		Knjiga knjiga = knjigaService.findOne(knjigaId);
		if(knjiga == null) {
			response.sendRedirect(baseURL);
		}
		
		Komentar komentar = new Komentar(tekstKomentara, ocena, datumPostavljanja, autor, knjiga, status);
		komentarService.save(komentar);
		
		response.sendRedirect("baseURL");
		
	}
	
	

}
