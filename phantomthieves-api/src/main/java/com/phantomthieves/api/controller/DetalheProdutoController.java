package com.phantomthieves.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.phantomthieves.api.model.Produto;
import com.phantomthieves.api.repository.ProdutoRepository;




@Controller
@RequestMapping("/produtos")

public class DetalheProdutoController {

@Autowired
private ProdutoRepository produtoRepository;
	
	@GetMapping
	public ModelAndView listar() {
		ModelAndView resultado = new ModelAndView("index");
		List<Produto> produto = produtoRepository.findAll();
		resultado.addObject("produto", produto);
		return resultado;
	}

	@GetMapping("/detalheProduto2")
		public ModelAndView detalheProduto2(@ModelAttribute("cod_produto") Integer cod_produto) {
		ModelAndView resultado = new ModelAndView("produtos/detalheProduto2");
		List<Produto> produto = (List<Produto>) produtoRepository.findAllByCodigo(cod_produto);
		resultado.addObject("produto", produto);
		return resultado; 
	}
	
	
}
