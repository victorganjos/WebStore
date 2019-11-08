package com.phantomthieves.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.phantomthieves.api.model.Imagem;
import com.phantomthieves.api.model.ItemSelecionado;
import com.phantomthieves.api.repository.ImagemRepository;
import com.phantomthieves.api.repository.ProdutoRepository;

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
	public ModelAndView adicionarItem(@ModelAttribute("imagemId") Integer imagemId,
			@ModelAttribute("itensSelecionados1") List<ItemSelecionado> itensSelecionados,
			RedirectAttributes redirAttr) {

		Imagem carrinho = im.findImgByIdImage(imagemId);

		int qtSelecionada = 0;
		boolean itemExiste = false;
		int posicaoLista = 0;

		for (ItemSelecionado item : itensSelecionados) {
			if (item.getItem().getId() == carrinho.getId()) {
				itemExiste = true;

				item.setQtCarrinho(item.getQtCarrinho() + 1);
				item.setValorTotal(item.getQtCarrinho() * item.getItem().getPrecoProduto());

				break;
			}
		}

		if (!itemExiste) {
			ItemSelecionado itemSel = new ItemSelecionado(carrinho);
			qtSelecionada = itemSel.getQtCarrinho() + 1;

			itemSel.setQtCarrinho(qtSelecionada);
			itemSel.setValorTotal(itemSel.getQtCarrinho() * itemSel.getItem().getPrecoProduto());

			itensSelecionados.add(itemSel);
		}

		return new ModelAndView("redirect:/");
	}

	@ModelAttribute("itensSelecionados1")
	public List<ItemSelecionado> getItensSelecionados() {
		return new ArrayList<>();
	}

	@PostMapping("index")
	public ModelAndView pesquisar(@RequestParam("buscaProduto") String buscaProduto) {
		ModelAndView resultado = new ModelAndView("index");
		List<Imagem> imagens = im.findImgByNomeProduto(buscaProduto);
		resultado.addObject("imagem", imagens);
		return resultado;
	}

	/*
	 * @PostMapping("/carrinho/carrinho") public ModelAndView
	 * carrinho(@ModelAttribute("itensSelecionados1") List<ItemSelecionado>
	 * itensSelecionados) { ModelAndView resultado = new
	 * ModelAndView("carrinho/carrinho"); resultado.addObject("itensSelecionados",
	 * itensSelecionados);
	 * 
	 * return resultado; }
	 */

}
