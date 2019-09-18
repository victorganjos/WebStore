package com.phantomthieves.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.phantomthieves.api.model.Produto;
import com.phantomthieves.api.repository.ProdutoRepository;



@Controller
@RequestMapping("/produtos")
public class ProdutoController {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	
	@GetMapping("/listar")
	public ModelAndView listar() {
		ModelAndView resultado = new ModelAndView("produtos/listar");
		List<Produto> produtos = produtoRepository.findAll();
		resultado.addObject("produtos", produtos);
		return resultado;
	}
	
	
	@GetMapping("/inserir")
	public ModelAndView inserir() {
		ModelAndView resultado = new ModelAndView("produtos/inserir");
		resultado.addObject("produto", new Produto());
		return resultado;
		
	}
	
	@PostMapping("/inserir")
	public String inserir(Produto produto) {
		produtoRepository.save(produto);
		return "redirect:/produtos/listar";
	}
	
	@GetMapping({"/pesquisarPorNome/{nomeProduto}", "/pesquisarPorNome"})
	public @ResponseBody List<Produto> pesquisarPorNome(@PathVariable Optional<String> nomeProduto){
		if(nomeProduto.isPresent()) {
			return produtoRepository.findByNomeProdutoContaining(nomeProduto.get()); 
		}else {
			return produtoRepository.findAll();
		}
		
	}
}
