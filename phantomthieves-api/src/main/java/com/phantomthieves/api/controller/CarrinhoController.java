package com.phantomthieves.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/carrinho")
public class CarrinhoController {
	 
	
	@GetMapping("/carrinho")
	public ModelAndView inserir() {
		ModelAndView resultado = new ModelAndView("carrinho/carrinho");
		//resultado.addObject("carrinho", new Carrinho());
		return resultado; 
	}


}
