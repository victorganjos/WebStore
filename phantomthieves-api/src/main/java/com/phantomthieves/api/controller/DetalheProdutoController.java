package com.phantomthieves.api.controller;

import java.awt.Image;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.phantomthieves.api.model.Cliente;
import com.phantomthieves.api.model.Endereco;
import com.phantomthieves.api.model.Imagem;
import com.phantomthieves.api.model.Produto;
import com.phantomthieves.api.repository.ProdutoRepository;

@Controller
@RequestMapping("/produtos")
public class DetalheProdutoController {

	@Autowired
	private ProdutoRepository produtoRepository;
	

	@PostMapping("/detalheProduto2")
	public ModelAndView detalhe(@ModelAttribute("imagemNomeProduto")String nomeProduto) {
		ModelAndView detalhes = new ModelAndView("produtos/detalheProduto2");
		Produto produto = new Produto();
		produto = produtoRepository.findByNomeProduto(nomeProduto);
				
		detalhes.addObject("produto", produto);

		return detalhes;

	}
}
