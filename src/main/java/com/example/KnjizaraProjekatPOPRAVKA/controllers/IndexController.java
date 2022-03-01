package com.example.KnjizaraProjekatPOPRAVKA.controllers;

import java.io.IOException;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.LocaleResolver;


@Controller
@RequestMapping(value="/")
public class IndexController implements ServletContextAware {
	
	@Autowired
	private ServletContext servletContext;
	private String baseURL;
	
	
	@GetMapping
	public String index() {
		return "index";
	}
	
	@Autowired
	private LocaleResolver localeResolver;
	
	@GetMapping("menjajLokalizacijuNaSrpski")
	public void index2(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		System.out.println("postavljanje lokalizacije za sesiju iz kontrolera na sr");
		localeResolver.setLocale(request, response, Locale.forLanguageTag("sr"));
		
		response.sendRedirect(baseURL);
	}	
	
	@GetMapping("menjajLokalizacijuNaEngleski")
	public void index3(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		System.out.println("postavljanje lokalizacije za sesiju iz kontrolera na en");
		localeResolver.setLocale(request, response, Locale.forLanguageTag("en"));
		
		response.sendRedirect(baseURL);
	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
		
	}	
	
	@PostConstruct
	public void init() {
		baseURL = servletContext.getContextPath() + "/";
	}
}
