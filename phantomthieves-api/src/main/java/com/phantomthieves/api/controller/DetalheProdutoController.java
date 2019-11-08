package com.phantomthieves.api.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.phantomthieves.api.model.Produto;
import com.phantomthieves.api.repository.ProdutoRepository;




@Controller
@RequestMapping("/produtos")

public class DetalheProdutoController {

private ProdutoRepository produtoRepository;
	
	@GetMapping
	public ModelAndView listar() {
		ModelAndView resultado = new ModelAndView("index");
		List<Produto> produto = produtoRepository.findAll();
		resultado.addObject("produto", produto);

		return resultado;
	}

	@GetMapping("/detalheProduto2")
	
	public ModelAndView detalheProduto2(Model model) {
		ModelAndView resultado = new ModelAndView("produtos/detalheProduto2");
		return resultado; 
	}
	
	
}
