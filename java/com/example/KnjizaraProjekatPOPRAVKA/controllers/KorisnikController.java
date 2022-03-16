package com.example.KnjizaraProjekatPOPRAVKA.controllers;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;

import com.example.KnjizaraProjekatPOPRAVKA.model.Korisnik;
import com.example.KnjizaraProjekatPOPRAVKA.model.Kupovina;
import com.example.KnjizaraProjekatPOPRAVKA.model.ListaZelja;
import com.example.KnjizaraProjekatPOPRAVKA.service.KorisnickaKorpaService;
import com.example.KnjizaraProjekatPOPRAVKA.service.KorisnikService;
import com.example.KnjizaraProjekatPOPRAVKA.service.KupovinaService;
import com.example.KnjizaraProjekatPOPRAVKA.service.ListaZeljaService;


@Controller
@RequestMapping(value="/Korisnici")
public class KorisnikController {
	
	public static final String KORISNIK_KEY = "prijavljeniKorisnik";
	public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy. HH:mm");

	@Autowired
	private LocaleResolver localeResolver;
	
	@Autowired
	private KorisnikService korisnikService;
	
	@Autowired
	private KorisnickaKorpaService korisnickaKorpaService;
	
	@Autowired
	private KupovinaService kupovinaService;
	
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
			@RequestParam(required=false) String ime,
			@RequestParam(required=false) String prezime,
			@RequestParam(required=false) String email,
			@RequestParam(required=false) String adresa,
			@RequestParam(required=false) String telefon,
			@RequestParam(required=false) String korisnickoIme,
			@RequestParam(required=false) String lozinka,
			@RequestParam(required=false)  @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) LocalDate datumRodjenja, 
			@RequestParam(required=false) @DateTimeFormat(iso=DateTimeFormat.ISO.TIME) LocalDateTime datumRegistracije, 
			@RequestParam(required=false) String uloga,
			@RequestParam(required=false) Boolean blokiran,

			HttpSession session, HttpServletResponse response) throws IOException{
		
			Korisnik prijavljeniKorisnik = (Korisnik) session.getAttribute(KorisnikController.KORISNIK_KEY);
			if(prijavljeniKorisnik == null ) {
				response.sendRedirect(baseURL);
				return null;
			}
			if(ime!=null && ime.trim().equals(""))
				ime=null;
			
			if(prezime!=null && prezime.trim().equals(""))
				prezime=null;
			
			if(email!=null && email.trim().equals(""))
				email=null;
			
			if(adresa!=null && adresa.trim().equals(""))
				adresa=null;
			
			if(telefon!=null && telefon.trim().equals(""))
				telefon=null;
			
			if(korisnickoIme!=null && korisnickoIme.trim().equals(""))
				korisnickoIme=null;
			
			if(datumRodjenja!=null)
				datumRodjenja=null;
			
			if(datumRegistracije!=null)
				datumRegistracije=null;
			
			if(uloga!=null && uloga.trim().equals(""))
				uloga=null;
			
			List<Korisnik> korisnici = korisnikService.findAll();
			
			ModelAndView rezultat = new ModelAndView("korisnici");
			rezultat.addObject("korisnici", korisnici);
			
			return rezultat;
	}
	
	
	@PostMapping(value="/Login")
	public ModelAndView postLogin(@RequestParam String korisnickoIme, @RequestParam String lozinka, @RequestParam(required=false) Boolean blokiran,
			HttpSession session, HttpServletResponse response) throws IOException{
		
				try {
					Korisnik korisnik = korisnikService.findOne(korisnickoIme, lozinka);
					if(korisnik == null) {
						throw new Exception("Neispravno korisnicko ime ili lozinka!");
					}
					
					if(korisnik.getKorisnickoIme() == null || korisnik.getKorisnickoIme() == "") {
						response.sendRedirect(baseURL);
					}
					if(korisnik.isBlokiran() == true) {
						throw new Exception("Blokirani ste usled neprimerenog ponasanja!");
					}
					
					if(lozinka==null || lozinka.trim().equals("")) {
						response.sendRedirect(baseURL);
						return null;
					}
					
			
					korisnik.setPrijavljen(true);
					session.setAttribute(KorisnikController.KORISNIK_KEY, korisnik);	
					response.sendRedirect(baseURL);
					return null;
						
				} catch(Exception ex) {
					String poruka = ex.getMessage();
					if(poruka=="") {
						poruka = "Neuspesno!";
					}
					ModelAndView rezultat = new ModelAndView("login");
					rezultat.addObject("poruka", poruka);
					
					return rezultat;
				}
			}
	
	
	@GetMapping("menjajLokalizacijuNaSrpski")
	public void index2(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		System.out.println("postavljanje lokalizacije za sesiju iz kontrolera na sr");
		localeResolver.setLocale(request, response, Locale.forLanguageTag("sr"));
		
		response.sendRedirect(baseURL+"Korisnici");
	}
	
	
	@GetMapping("menjajLokalizacijuNaEngleski")
	public void index3(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		System.out.println("postavljanje lokalizacije za sesiju iz kontrolera na en");
		localeResolver.setLocale(request, response, Locale.forLanguageTag("en"));
		
		response.sendRedirect(baseURL+"Korisnici");
	}	

	
	@PostMapping(value="/Register")
	public ModelAndView register(@RequestParam String ime,
			@RequestParam String prezime,
			@RequestParam String email,
			@RequestParam(required=false) String adresa,
			@RequestParam (required=false) String telefon,
			@RequestParam String korisnickoIme, 
			@RequestParam String lozinka,
			@RequestParam String ponovljenaLozinka,
			@RequestParam @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) LocalDate datumRodjenja,
			@RequestParam(required=false)@DateTimeFormat(iso=DateTimeFormat.ISO.DATE_TIME) LocalDateTime datumRegistracije,
			@RequestParam String uloga,
			HttpSession session, HttpServletResponse response) throws IOException{
		try {
			Korisnik postojeciKorisnik = korisnikService.findOne(korisnickoIme);
			if(postojeciKorisnik != null) {
				throw new Exception("Korisnicko ime vec postoji!");
			}
			if(korisnickoIme.equals("") || lozinka.equals("")){
				throw new Exception("Korisnicko ime i lozinka moraju da se unesu!");
			}
			if(!lozinka.equals(ponovljenaLozinka)) {
				throw new Exception("Lozinke se ne podudaraju!");
			}
			if(ime.equals("")) {
				throw new Exception("Ime polje ne sme biti prazno!");
			}
			if(prezime.equals("")) {
				throw new Exception("Prezime polje ne sme biti prazno!");
			}
			if(email.equals("")) {
				throw new Exception("Email polje ne sme biti prazno!");
				
			}
			if(adresa.equals("")) {
				throw new Exception("Adresa polje ne sme biti prazno!");
			}
			if(telefon.equals("")) {
				throw new Exception("Telefon polje ne sme biti prazno!");
			}
			if(datumRodjenja.equals("")) {
				throw new Exception("Datum rodjenja polje ne sme biti prazno!");
			}
		
			
			Korisnik korisnik = new Korisnik(ime, prezime, email, adresa, telefon, korisnickoIme, lozinka, datumRodjenja,datumRegistracije,uloga,false,false);
			korisnikService.save(korisnik);
			
			response.sendRedirect(baseURL+"login.html");
			return null;
			
			}catch(Exception ex) {
				String poruka = ex.getMessage();
				if(poruka == "") {
					poruka = "Neuspesna registracija!";
			}
			
			ModelAndView rezultat = new ModelAndView("registracija");
			rezultat.addObject("poruka", poruka);
			
			return rezultat;
			
		}	
		
	}
	
	
	@GetMapping(value="/Details")
	public ModelAndView details(@RequestParam String korisnickoIme, HttpSession session, HttpServletRequest request,
				HttpServletResponse response) throws IOException {
					
					Korisnik korisnik = korisnikService.findOne(korisnickoIme);
					List<ListaZelja> listaZelja = listaZeljaService.findAll();
					List<Kupovina> kupovine = kupovinaService.findAll();
					
					ModelAndView rezultat = new ModelAndView("korisnik");
					
					rezultat.addObject("korisnik", korisnik);
					rezultat.addObject("listaZelja", listaZelja);
					rezultat.addObject("kupovine", kupovine);
		
					return rezultat;
				}
				
	
	@PostMapping(value="/Edit")
	public void edit(	@RequestParam(required=false) String ime,
						@RequestParam(required=false) String prezime,
						@RequestParam(required=false) String email,
						@RequestParam(required=false) String adresa,
						@RequestParam (required=false) String telefon,
						@RequestParam(required=false) String korisnickoIme, 
						@RequestParam(required=false) String lozinka,
						@RequestParam(required=false) Boolean blokiran,
						//@RequestParam(required=false) @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) LocalDate datumRodjenja,
						//@RequestParam(required=false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDateTime datumRegistracije,
						@RequestParam(required=false) String uloga,
					HttpSession session, HttpServletResponse response) throws IOException{
		
					Korisnik prijavljeniKorisnik = (Korisnik) session.getAttribute(KorisnikController.KORISNIK_KEY);
					
					if (prijavljeniKorisnik == null) {
						response.sendRedirect(baseURL);
						return;
					}
				
					Korisnik korisnik = korisnikService.findOne(korisnickoIme);
					if(korisnik == null) {
						response.sendRedirect(baseURL + "Korisnici");
					}
					
					korisnik.setAdresa(adresa);
					korisnik.setUloga(uloga);
					korisnik.setBlokiran(blokiran != null);
					//knjiga.setProsecnaOcena(prosecnaOcena); //Aplikacija resava(Barem bi trebalo)
					
					korisnikService.update(korisnik);
					response.sendRedirect(baseURL+"Korisnici");
					
					}
	
	
	@PostMapping("/Loginn")
	public void loginn(@RequestParam String korisnickoIme, @RequestParam String lozinka,
	HttpSession session, HttpServletResponse response) throws IOException{
		try {
		Korisnik korisnik = korisnikService.findOne(korisnickoIme, lozinka);
		
		if(korisnickoIme == null || korisnickoIme.trim().equals("")) {
			response.sendRedirect(baseURL+"login.html");
			return;
		}
		if(lozinka==null || lozinka.trim().equals("")) {
			response.sendRedirect(baseURL+"login.html");
			return;
		}
		if(korisnik.getKorisnickoIme()==null) {
			response.sendRedirect(baseURL+"login.html");
			return;
		}
		if(!korisnik.getLozinka().equals(lozinka)) {
			response.sendRedirect(baseURL+"login.html");
			return;
		}
		korisnik.setPrijavljen(true);
		session.setAttribute(KorisnikController.KORISNIK_KEY, korisnik);	
		response.sendRedirect(baseURL+"/helloWorld");
		}catch(Exception ex) {
			String poruka = ex.getMessage();
			if(poruka=="") {
				poruka = "Neuspesno!";
			}
			
			response.sendRedirect(baseURL+"helloWorld");
		}
		
	}
	
	
	@GetMapping(value="/Logout")
	public void logout(HttpSession session, HttpServletResponse response) throws IOException {
					
		session.invalidate();
		
		response.sendRedirect(baseURL);
		
		
	}
	
}
