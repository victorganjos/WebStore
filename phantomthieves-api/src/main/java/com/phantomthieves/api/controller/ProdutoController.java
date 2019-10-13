package com.phantomthieves.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
		List<Produto> produtos = produtoRepository.findAllAtivo();
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
		return "redirect:/imagem/uploadMultiFile";
	}

	@GetMapping("/editar/{id}")
	public ModelAndView editar(@PathVariable Integer id) {
		ModelAndView resultado = new ModelAndView("produtos/editar");
		Produto produto = produtoRepository.getOne(id);
		resultado.addObject("produto", produto);
		return resultado;

	}

	@GetMapping("/excluir/{id}")
	@Transactional
	public String excluir(@PathVariable Integer id) {
		produtoRepository.deleteDesativo(id);
		return "redirect:/produtos/listar";

	}

	@PostMapping("/editar/{produto.id}")
	public String alterar(Produto produto) {
		produtoRepository.save(produto);
		return "redirect:/produtos/listar";
	}

	@GetMapping({ "/listar/{nomeProduto}", "/pesquisarPorNome" })
	public @ResponseBody List<Produto> pesquisarPorNome(@PathVariable Optional<String> nomeProduto) {
		if (nomeProduto.isPresent()) {
			return produtoRepository.findByNomeProdutoContaining(nomeProduto.get());
		} else {
			return produtoRepository.findAll();
		}

	}
	
	@PostMapping("/listar")
	public ModelAndView pesquisar(@RequestParam("nomepesquisa") String nomePessoa) {
		ModelAndView resultado = new ModelAndView("produtos/listar");
		List<Produto> produtos = produtoRepository.findAllNomeProduto(nomePessoa);
		resultado.addObject("produtos", produtos);
		return resultado;
	}
}
