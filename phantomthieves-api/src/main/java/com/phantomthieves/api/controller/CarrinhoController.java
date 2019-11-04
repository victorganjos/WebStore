package com.phantomthieves.api.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.phantomthieves.api.model.ItemSelecionado;

@Controller
@RequestMapping("/carrinho")
@SessionAttributes("itensSelecionados1")
public class CarrinhoController {
	 
	
	@GetMapping("/carrinho")
	public ModelAndView inserir(@ModelAttribute("itensSelecionados1") List<ItemSelecionado> itensSelecionados1) {
		ModelAndView resultado = new ModelAndView("carrinho/carrinho");
		
		resultado.addObject("itensSelecionados1", itensSelecionados1);
		
		return resultado; 
	}
	
	@PostMapping("/carrinho")
	public ModelAndView addRemoverCarrinho(@ModelAttribute("imagemId") Integer imagemId,
			@ModelAttribute("itensSelecionados1") List<ItemSelecionado> itensSelecionados1,
			RedirectAttributes redirAttr) {
		int index = 0;
		
		for(ItemSelecionado item: itensSelecionados1) {	
			if (item.getItem().getId() == imagemId){
				itensSelecionados1.remove(index);
				break;
			}
			
			index++;
		}
		return new ModelAndView("redirect:/carrinho/carrinho");
	}

}