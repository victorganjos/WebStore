package com.phantomthieves.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.phantomthieves.api.model.Imagem;
import com.phantomthieves.api.model.Produto;
import com.phantomthieves.api.repository.ImagemRepository;
import com.phantomthieves.api.repository.ProdutoRepository;

@Controller
@RequestMapping("/produtos")
public class DetalheProdutoController {
	
	@Autowired
	private ImagemRepository im;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	

	@PostMapping("/detalheProduto2")
	public ModelAndView detalhe(@ModelAttribute("imagemNomeProduto")String nomeProduto,
			@ModelAttribute("imagemIdProduto")Integer id) {
		ModelAndView resultado = new ModelAndView("produtos/detalheProduto2");
		String local = "";
		List<Imagem> imagem = im.findImg();
		resultado.addObject("imagem", imagem);
		Produto produto = new Produto();
		for(Imagem i: imagem) {

			if(i.getCodProduto().getId() == id) {
				produto = i.getCodProduto();
				local = i.getLocalArquivo();
			}
		}
		
		System.out.println("teste detalhamento: " +nomeProduto+ " ID: " +id+ " Local da Imagem: " +local);

		resultado.addObject("local", local);
		resultado.addObject("produto", produto);
		resultado.addObject("id", id);
		return resultado;

	}
}
