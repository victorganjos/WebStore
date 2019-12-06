package com.phantomthieves.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.phantomthieves.api.model.Produto;
import com.phantomthieves.api.repository.ImagemRepository;
import com.phantomthieves.api.repository.ProdutoRepository;

@Controller
@RequestMapping("/")
@SessionAttributes("itensSelecionados1")
public class HomeController {

	@Autowired
	private ImagemRepository im;

	@Autowired
	private ProdutoRepository produtoRepository;

	@GetMapping
	public ModelAndView listar(String msg) {
		ModelAndView resultado = new ModelAndView("index");
		List<Imagem> imagem = im.findImg();
		List<Imagem> aux = new ArrayList<Imagem>();
		
		for(Imagem img: imagem) {
			if(img.getCodProduto().getQuantidadeProduto()>0) {
			aux.add(img);
			}
		}
		resultado.addObject("imagem", aux);
		resultado.addObject("msg", msg);

		return resultado;
	}

	@GetMapping("/listarConsole")
	public ModelAndView listarConsole() {
		ModelAndView resultado = new ModelAndView("index");
		List<Imagem> imagem = im.findImg();
		List<Imagem> imagemConsole = new ArrayList<>();
		for (Imagem i : imagem) {
			if (i.getCodProduto().getCategoriaProduto().equals("console") && (i.getCodProduto().getQuantidadeProduto()>0)) {
				imagemConsole.add(i);
			}
		}
		resultado.addObject("imagem", imagemConsole);

		return resultado;
	}

	@GetMapping("/listarAcessorios")
	public ModelAndView listarAcessorios() {
		ModelAndView resultado = new ModelAndView("index");
		List<Imagem> imagem = im.findImg();
		List<Imagem> imagemConsole = new ArrayList<>();
		for (Imagem i : imagem) {
			if (i.getCodProduto().getCategoriaProduto().equals("acessorio") && (i.getCodProduto().getQuantidadeProduto()>0)) {
				imagemConsole.add(i);
			}
		}
		resultado.addObject("imagem", imagemConsole);

		return resultado;
	}

	@GetMapping("/listarJogos")
	public ModelAndView listarJogos() {
		ModelAndView resultado = new ModelAndView("index");
		List<Imagem> imagem = im.findImg();
		List<Imagem> imagemConsole = new ArrayList<>();
		for (Imagem i : imagem) {
			if (i.getCodProduto().getCategoriaProduto().equals("jogo") && (i.getCodProduto().getQuantidadeProduto()>0)) {
				imagemConsole.add(i);
			}
		}
		resultado.addObject("imagem", imagemConsole);

		return resultado;
	}

	@PostMapping
	public ModelAndView adicionarItem(@ModelAttribute("imagemId") Integer imagemId,
			@ModelAttribute("itensSelecionados1") List<ItemSelecionado> itensSelecionados,
			RedirectAttributes redirAttr) {
	
		Imagem carrinho = im.findImgByIdImage(imagemId);
		Produto produto = new Produto();
		int qtSelecionada = 0;
		boolean itemExiste = false;
		int posicaoLista = 0;
		
		produto = produtoRepository.buscaPorId(imagemId);
		
		for(int i = 0; i < itensSelecionados.size(); i++) {
			if(produto.getNomeProduto().equals(itensSelecionados.get(i).getItem().getNomeProduto())) {
				if(produto.getQuantidadeProduto() == itensSelecionados.get(i).getQtCarrinho()) {
					return listar("0");
				}else {
					continue;
				}
			}
		}
		
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
		System.out.println("\n\n Quero fazer um teste aqui: " +itensSelecionados.get(0).getItem().getNomeProduto());
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
		List<Imagem> aux = new ArrayList<>();
		
		for(Imagem img: imagens) {
			if(img.getCodProduto().getQuantidadeProduto()>0) {
				aux.add(img);
				}
			}
			resultado.addObject("imagem", aux);

		return resultado;
	}

}
