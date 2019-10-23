package com.phantomthieves.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.phantomthieves.api.model.Imagem;
import com.phantomthieves.api.repository.ImagemRepository;

import com.phantomthieves.api.controller.ItemSelecionado;


@Controller
@RequestMapping("/")
@SessionAttributes("itensSelecionados1")
public class HomeController {

	@Autowired
	private ImagemRepository im;
	
	@GetMapping
	public ModelAndView listar() {
		ModelAndView resultado = new ModelAndView("index");
		List<Imagem> imagem = im.findImg();
		resultado.addObject("imagem", imagem);

		return resultado;
	}
	
	@PostMapping
	public ModelAndView adicionarItem(
			@ModelAttribute("imagemId") Integer imagemId,
			@ModelAttribute("itensSelecionados1") List<ItemSelecionado> itensSelecionados,
			RedirectAttributes redirAttr) {
		
		System.out.println("");
		System.out.println("");
		System.out.println(imagemId);
		System.out.println("");
		System.out.println("");
		
		Imagem carrinho = im.findImgByIdImage(imagemId);
		itensSelecionados.add(new ItemSelecionado(carrinho));
		redirAttr.addFlashAttribute("msg", "Imagem ID " + carrinho.getId() + " adicionado com sucesso");
		return new ModelAndView("redirect:/");
	}
	
	@ModelAttribute("itensSelecionados1")
	public List<ItemSelecionado> getItensSelecionados() {
		return new ArrayList<>();
	}
}
