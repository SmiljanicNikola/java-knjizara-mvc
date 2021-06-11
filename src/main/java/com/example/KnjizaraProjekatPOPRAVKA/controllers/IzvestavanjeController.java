package com.example.KnjizaraProjekatPOPRAVKA.controllers;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.annotation.PostConstruct;
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

import com.example.KnjizaraProjekatPOPRAVKA.model.Kupovina;
import com.example.KnjizaraProjekatPOPRAVKA.service.KnjigaService;
import com.example.KnjizaraProjekatPOPRAVKA.service.KupovinaService;


@Controller
@RequestMapping(value="/Izvestaj")
public class IzvestavanjeController implements ServletContextAware{
	
	/*public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM.dd.yyyy. HH:mm");
	public static final String minDate = LocalDate.MIN.format(DateTimeFormatter.ofPattern("MM.dd.yyyy."));
	public static final String maxDate = LocalDate.MAX.format(DateTimeFormatter.ofPattern("MM.dd.yyyy."));*/

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
		
	}
	
	@Autowired
	private ServletContext servletContext;
	private String baseURL;

	@Autowired
	private KnjigaService knjigaService;
	
	@Autowired
	private KupovinaService kupovinaService;
	
	@Autowired
	private LocaleResolver localeResolver;
	
	@PostConstruct
	public void init() {
		baseURL = servletContext.getContextPath() + "/";
	}
	
	@GetMapping
	public ModelAndView index(
			@RequestParam(required=false) Integer id,
			@RequestParam(required=false) Integer knjigaId,
			@RequestParam(required=false) Float ukupnaCena,
			@RequestParam(required=false) @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) LocalDate datumKupovineOd,
			@RequestParam(required=false) @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) LocalDate datumKupovineDo,
			@RequestParam(required=false) String musterijaOznaka,
			@RequestParam(required=false) Integer brojKupljenihKnjiga,
			HttpSession session) throws IOException{
		
			//List<Kupovina> kupovine = kupovinaService.findAll();
			List<Kupovina> kupovine = kupovinaService.find2(datumKupovineOd,datumKupovineDo);
			
			
			ModelAndView rezultat = new ModelAndView("izvestaj");
			rezultat.addObject("kupovine", kupovine);
			
			return rezultat;
		
			}
	
	
}
