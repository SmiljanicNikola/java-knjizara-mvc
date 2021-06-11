package com.example.KnjizaraProjekatPOPRAVKA.controllers;
import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
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

import com.example.KnjizaraProjekatPOPRAVKA.model.Knjiga;
import com.example.KnjizaraProjekatPOPRAVKA.model.Korisnik;
import com.example.KnjizaraProjekatPOPRAVKA.model.ListaZelja;
import com.example.KnjizaraProjekatPOPRAVKA.service.KnjigaService;
import com.example.KnjizaraProjekatPOPRAVKA.service.KorisnikService;
import com.example.KnjizaraProjekatPOPRAVKA.service.ListaZeljaService;


@Controller
@RequestMapping(value="/ListaZelja")
public class ListaZeljaController implements ServletContextAware {

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
		
	}

	@Autowired
	private KorisnikService korisnikService;
	
	@Autowired
	private KnjigaService knjigaService;
	
	@Autowired
	private ListaZeljaService listaZeljaService;
		
	
	@Autowired
	private ServletContext servletContext;
	private String baseURL;
	
	@PostConstruct
	public void init() {
		baseURL = servletContext.getContextPath() + "/";
	}
	
	@GetMapping
	public ModelAndView index(
			@RequestParam(required=false) Integer knjigaId,
			@RequestParam(required=false) String vlasnikListeOznaka,
			HttpSession session
			)throws IOException{
				List<ListaZelja> stavkeListeZelja = listaZeljaService.findAll();
				
				ModelAndView rezultat = new ModelAndView("korisnik");
				rezultat.addObject("stavkeListeZelja", stavkeListeZelja);
				
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
			HttpSession session, HttpServletResponse response) throws IOException{
		
		Korisnik prijavljeniKorisnik = (Korisnik) session.getAttribute(KorisnikController.KORISNIK_KEY);
		if(prijavljeniKorisnik == null) {
			response.sendRedirect(baseURL + "Knjiga");
			return;
		}
		
		Knjiga knjiga = knjigaService.findOne(knjigaId);
		if(knjiga == null) {
			response.sendRedirect(baseURL);
			return;
		}
		
		Korisnik vlasnikListe = korisnikService.findOne(prijavljeniKorisnik.getKorisnickoIme());
		if(vlasnikListe == null) {
			response.sendRedirect(baseURL);
			return;
		}
		
		ListaZelja listaZelja = new ListaZelja(knjiga, vlasnikListe);
		listaZeljaService.save(listaZelja);
		
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
		
		listaZeljaService.delete(id);
		
		
		
	}
				
	/*@GetMapping(value="/Details")
	public ModelAndView details(@RequestParam int id, HttpSession session, HttpServletRequest request,
				HttpServletResponse response) throws IOException {
					
					ListaZelja listaZelja = listaZeljaService.findOne(id);
					List<ListaZelja> listaZelja = ListaZeljaService.findAll();
					
					ModelAndView rezultat = new ModelAndView("korisnik");
					
					rezultat.addObject("korisnik", korisnik);
					rezultat.addObject("listaZelja", listaZelja);

					
					return rezultat;
				}*/
	
	@PostMapping(value="/Delete")
	public void Delete(@RequestParam int id, 
			HttpSession session, HttpServletResponse response) throws IOException {
		// autentikacija, autorizacija
		Korisnik prijavljeniKorisnik = (Korisnik) session.getAttribute(KorisnikController.KORISNIK_KEY);
		if (prijavljeniKorisnik == null) {
			response.sendRedirect(baseURL + "Knjige");
			return;
		}

		listaZeljaService.delete(id);

		//response.sendRedirect(baseURL + "Korisnik");
		
	}
	
}
