package com.phantomthieves.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.phantomthieves.api.model.Imagem;
import com.phantomthieves.api.repository.ImagemRepository;


@Controller
public class HomeController {
	

	
	@Autowired
	private ImagemRepository im;
	
	@RequestMapping("/")
		public ModelAndView listar() {
			ModelAndView resultado = new ModelAndView("index");
			List<Imagem> imagem = im.findImg();
			resultado.addObject("imagem", imagem);
	
			return resultado;
	}
}
