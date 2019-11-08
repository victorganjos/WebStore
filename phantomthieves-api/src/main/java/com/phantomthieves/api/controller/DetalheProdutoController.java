package com.phantomthieves.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/produtos")

public class DetalheProdutoController {
	@GetMapping("/detalheProduto2")
	
	public ModelAndView detalheProduto2(Model model) {
		ModelAndView resultado = new ModelAndView("produtos/detalheProduto2");
		return resultado; 
	}
}
