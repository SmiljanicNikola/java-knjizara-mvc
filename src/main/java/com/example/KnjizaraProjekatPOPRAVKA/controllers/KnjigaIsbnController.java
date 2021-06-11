package com.example.KnjizaraProjekatPOPRAVKA.controllers;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;

import com.example.KnjizaraProjekatPOPRAVKA.model.Knjiga;
import com.example.KnjizaraProjekatPOPRAVKA.model.Zanr;
import com.example.KnjizaraProjekatPOPRAVKA.service.KnjigaService;
import com.example.KnjizaraProjekatPOPRAVKA.service.KomentarService;
import com.example.KnjizaraProjekatPOPRAVKA.service.ZanrService;


@Controller
@RequestMapping(value="/KnjigeIsbn")
public class KnjigaIsbnController implements ServletContextAware {


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
	
	
	@GetMapping
	public ModelAndView index(@RequestParam(required=false) Integer id,
							 @RequestParam(required=false) String naziv,
							 @RequestParam(required=false) Long isbn,
							 @RequestParam(required=false) String izdavackaKuca,
							 @RequestParam(required=false) String autor,
							 @RequestParam(required=false) @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) LocalDate godinaIzdavanja, 
							 @RequestParam(required=false) String kratakOpis,
							 @RequestParam(required=false) Float cena,
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
							List<Knjiga> knjige = knjigaService.find3(naziv, isbn, izdavackaKuca, autor, kratakOpis, cena, brojStranica, tipPoveza, pismo, jezik, prosecnaOcena,brojPrimeraka, zanrId);
							List<Zanr> zanrovi = zanrService.findAll();

							
							//List<Knjiga> knjige = knjigaService.findVerzija2(id, naziv, isbn, izdavackaKuca, autor, godinaIzdavanja, kratakOpis, cena, brojStranica, tipPoveza, pismo, jezik, prosecnaOcena);
							
							ModelAndView rezultat = new ModelAndView("knjigeisbn");
							rezultat.addObject("knjige", knjige);
							rezultat.addObject("zanrovi", zanrovi);
							
							return rezultat;			
	}
	
	
}
