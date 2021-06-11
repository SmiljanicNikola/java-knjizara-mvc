package com.example.KnjizaraProjekatPOPRAVKA.controllers;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
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
import com.example.KnjizaraProjekatPOPRAVKA.model.Zanr;
import com.example.KnjizaraProjekatPOPRAVKA.service.KnjigaService;
import com.example.KnjizaraProjekatPOPRAVKA.service.KomentarService;
import com.example.KnjizaraProjekatPOPRAVKA.service.ZanrService;


@Controller
@RequestMapping(value="/Knjige")
public class KnjigaController implements ServletContextAware {

	public static final String KNJIGA_KEY = "knjiga";
	
	@Autowired
	private KnjigaService knjigaService;
	
	@Autowired
	private ZanrService zanrService;
	
	@Autowired
	private LocaleResolver localeResolver;
	
	@Autowired
	private KomentarService komentarService;
	
	@Autowired
	private ServletContext servletContext;
	private String baseURL;
	
	@Override
	public void setServletContext(ServletContext servletContext) {
			this.servletContext = servletContext;
	}
	
	@PostConstruct
	public void init() {
		baseURL = servletContext.getContextPath() + "/";
	}
	
	@GetMapping
	public ModelAndView index(@RequestParam(required=false) Integer id,
							 @RequestParam(required=false) String naziv,
							 @RequestParam(required=false) Long isbn,
							 @RequestParam(required=false) String izdavackaKuca,
							 @RequestParam(required=false) String autor,
							 @RequestParam(required=false) @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) LocalDate godinaIzdavanja, 
							 @RequestParam(required=false) String kratakOpis,
							 @RequestParam(required=false) Float cenaOd,
							 @RequestParam(required=false) Float cenaDo,
							 @RequestParam(required=false) Integer brojStranica,
							 @RequestParam(required=false) String tipPoveza,
							 @RequestParam(required=false) String pismo,
							 @RequestParam(required=false) String jezik,
							 @RequestParam(required=false) Float prosecnaOcena,
							 @RequestParam(required=false) Integer zanrId,
							 @RequestParam(required=false) Integer brojPrimeraka,

							 HttpSession session) throws IOException{
							
		
							/*List<Knjiga> knjige = knjigaService.find(naziv, cena, autor, jezik);
							 * 
						
							 */
		
							//Ovo
							//List<Knjiga> knjige = knjigaService.findVerzija2(naziv, isbn, izdavackaKuca, autor, godinaIzdavanja, kratakOpis, cena, brojStranica, tipPoveza, pismo, jezik, prosecnaOcena);
							
							
							
							//List<Knjiga> knjige = knjigaService.find(id, naziv, isbn, izdavackaKuca, autor, godinaIzdavanja, kratakOpis, cena, brojStranica, tipPoveza, pismo, jezik, prosecnaOcena);
						//List<Knjiga> knjige = knjigaService.findAll();
							List<Knjiga> knjige = knjigaService.find2(naziv, isbn, izdavackaKuca, autor, kratakOpis, cenaOd, cenaDo, brojStranica, tipPoveza, pismo, jezik, prosecnaOcena,brojPrimeraka, zanrId);
							List<Zanr> zanrovi = zanrService.findAll();

							
							//List<Knjiga> knjige = knjigaService.findVerzija2(id, naziv, isbn, izdavackaKuca, autor, godinaIzdavanja, kratakOpis, cena, brojStranica, tipPoveza, pismo, jezik, prosecnaOcena);
							
							ModelAndView rezultat = new ModelAndView("knjige");
							rezultat.addObject("knjige", knjige);
							rezultat.addObject("zanrovi", zanrovi);
							
							return rezultat;			
	}
	
	/*List<Komentar> komentari = komentarService.findAll();
	List<Komentar> odgovarajuci = new ArrayList<Komentar>();
	public List<Komentar> PronadjiOdgovarajuce(){
		for(Komentar komentar : komentari) {

			if(komentar.getKnjiga().getNaziv() == Knjiga.getNaziv()){
				odgovarajuci.add(komentar);
				return odgovarajuci;

			}

		
			}
		}*/
	@GetMapping("menjajLokalizacijuNaSrpski")
	public void index2(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		System.out.println("postavljanje lokalizacije za sesiju iz kontrolera na sr");
		localeResolver.setLocale(request, response, Locale.forLanguageTag("sr"));
		
		response.sendRedirect(baseURL+"Knjige");
	}	
	
	@GetMapping("menjajLokalizacijuNaEngleski")
	public void index3(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		System.out.println("postavljanje lokalizacije za sesiju iz kontrolera na en");
		localeResolver.setLocale(request, response, Locale.forLanguageTag("en"));
		
		response.sendRedirect(baseURL+"Knjige");
	}	
	
	
	@GetMapping(value="/Details")
	public ModelAndView details(@RequestParam (required=false) Integer id,@RequestParam (required=false) String naziv, HttpSession session, HttpServletRequest request,
				HttpServletResponse response)  {
					
					Knjiga knjiga = knjigaService.findOne(naziv);
					//Knjiga knjiga2 = knjigaService.findOne(naziv);
					List<Komentar> komentari = komentarService.findAll();
					/*if(komentari == null) {
						komentari.set(1, new Komentar());
					}
					*/
					
					
					/*List<Komentar> odgovarajuci = new ArrayList<Komentar>();
					for(Komentar komentar : komentari) {
						if(komentar.getKnjiga().getNaziv() == knjiga.getNaziv())
							odgovarajuci.add(komentar);
							
						
						}*/
						
					ModelAndView rezultat = new ModelAndView("knjiga");
					rezultat.addObject("knjiga", knjiga);
					rezultat.addObject("komentari", komentari);
					
					return rezultat;
				}
				
				
	
	private void PronadjiOdgovarajuce() {
		// TODO Auto-generated method stub
		
	}

	@GetMapping(value="/Create")
	public ModelAndView create(HttpSession session, HttpServletResponse response) throws IOException {
		Korisnik prijavljeniKorisnik  = (Korisnik) session.getAttribute(KorisnikController.KORISNIK_KEY);
		if(prijavljeniKorisnik == null){
			response.sendRedirect(baseURL + "Knjige");
			return null;
		}
		List<Zanr> zanrovi = zanrService.findAll();

		// prosleđivanje
		ModelAndView rezultat = new ModelAndView("dodavanjeKnjige");
		rezultat.addObject("zanrovi", zanrovi);

		return rezultat;
	}
	
	@PostMapping(value="/Edit")
	public void edit(@RequestParam Integer id,
					@RequestParam String naziv,
					@RequestParam Long isbn,
					@RequestParam String izdavackaKuca,
					@RequestParam String autor,
					@RequestParam @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) LocalDate godinaIzdavanja,
					@RequestParam String kratakOpis,
					@RequestParam Float cena,
					@RequestParam Integer brojStranica,
					@RequestParam String tipPoveza,
					@RequestParam String pismo,
					@RequestParam String jezik,
					@RequestParam(required=false) Float prosecnaOcena,
					HttpSession session, HttpServletResponse response) throws IOException{
		
					Korisnik prijavljeniKorisnik = (Korisnik) session.getAttribute(KorisnikController.KORISNIK_KEY);
					
					if (prijavljeniKorisnik == null) {
						response.sendRedirect(baseURL + "Knjige");
						return;
					}
				
					Knjiga knjiga = knjigaService.findOne(id);
					if(knjiga == null) {
						response.sendRedirect(baseURL + "Knjige");
					}
					if (naziv == null || naziv.equals("")) {
						response.sendRedirect(baseURL + "Knjige/Details?id=" + id);
						return;
					}
					
					knjiga.setId(id);
					knjiga.setNaziv(naziv);
					//knjiga.setIsbn(isbn); //Administrator ne moze da menja ispb
					knjiga.setIzdavackaKuca(izdavackaKuca);
					knjiga.setAutor(autor);
					knjiga.setGodinaIzdavanja(godinaIzdavanja);
					knjiga.setKratakOpis(kratakOpis);
					knjiga.setCena(cena);
					knjiga.setBrojStranica(brojStranica);
					knjiga.setTipPoveza(tipPoveza);
					knjiga.setPismo(pismo);
					knjiga.setJezik(jezik);
					//knjiga.setProsecnaOcena(prosecnaOcena); //Aplikacija resava(Barem bi trebalo)
					
					knjigaService.update(knjiga);
					response.sendRedirect(baseURL+"Knjige");
					
					}
	
	
	@PostMapping(value="/Poruci")
	public void poruci(@RequestParam Integer id,
					@RequestParam(required=false) Integer brojPrimeraka,
					HttpSession session, HttpServletResponse response) throws IOException{
		
					Korisnik prijavljeniKorisnik = (Korisnik) session.getAttribute(KorisnikController.KORISNIK_KEY);
					
					if (prijavljeniKorisnik == null) {
						response.sendRedirect(baseURL + "Knjige");
						return;
					}
				
					Knjiga knjiga = knjigaService.findOne(id);
					if(knjiga == null) {
						response.sendRedirect(baseURL + "Knjige");
					}
					if (brojPrimeraka == null || brojPrimeraka.equals("")) {
						response.sendRedirect(baseURL + "Knjige/Details?id=" + id);
						return;
					}
					
					knjiga.setBrojPrimeraka(brojPrimeraka);
					//knjiga.setProsecnaOcena(prosecnaOcena); //Aplikacija resava(Barem bi trebalo)
					
					knjigaService.update2(knjiga);
					response.sendRedirect(baseURL+"Knjige");
					
					}
						
	
	@PostMapping(value="/Create")
	public void create(@RequestParam(required=false) Integer id,
			@RequestParam String naziv,
			@RequestParam Long isbn,
			@RequestParam String izdavackaKuca,
			@RequestParam String autor,
			@RequestParam @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) LocalDate godinaIzdavanja,
			@RequestParam String kratakOpis,
			@RequestParam Float cena,
			@RequestParam Integer brojStranica,
			@RequestParam String tipPoveza,
			@RequestParam String pismo,
			@RequestParam String jezik,
			@RequestParam(required=false) Float prosecnaOcena,
			@RequestParam(name="zanrId", required=false) Integer[] zanrIds,
			HttpSession session, HttpServletResponse response) throws IOException {
		
		Korisnik prijavljeniKorisnik  = (Korisnik) session.getAttribute(KorisnikController.KORISNIK_KEY);
		if(prijavljeniKorisnik == null){
			response.sendRedirect(baseURL + "Knjige");
			return;
		}
		
		Knjiga knjiga = new Knjiga(naziv,isbn,izdavackaKuca,autor, godinaIzdavanja, kratakOpis, cena, brojStranica, tipPoveza, pismo, jezik, prosecnaOcena);
		knjiga.setZanrovi(zanrService.find(zanrIds));

		knjigaService.save(knjiga);
		
		response.sendRedirect(baseURL + "Knjige");
	}
	
	@PostMapping(value="/Delete")
	public void Delete(@RequestParam int id, 
			HttpSession session, HttpServletResponse response) throws IOException {
		// autentikacija, autorizacija
		Korisnik prijavljeniKorisnik = (Korisnik) session.getAttribute(KorisnikController.KORISNIK_KEY);
		if (prijavljeniKorisnik == null) {
			response.sendRedirect(baseURL + "Knjige");
			return;
		}

		knjigaService.delete(id);

		response.sendRedirect(baseURL + "Knjige");
	}
	

}






		
	
