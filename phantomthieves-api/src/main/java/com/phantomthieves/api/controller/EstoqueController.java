package com.phantomthieves.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.phantomthieves.api.model.Pedido;
import com.phantomthieves.api.model.Produto;
import com.phantomthieves.api.repository.PedidoRepository;
import com.phantomthieves.api.repository.ProdutoRepository;


@Controller
@RequestMapping("/estoquista")
public class EstoqueController {
	
	@Autowired
	private PedidoRepository pedRepo;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@GetMapping("/listarPedidos")
	public ModelAndView listar() {
		ModelAndView resultado = new ModelAndView("estoquista/listarPedidos");
		String status = "";
		
		List<Pedido> pedidos = pedRepo.buscaTodosPedidosDecrescente();
		
		for(Pedido ped : pedidos) {
			status = ped.getStatusPedido();
			
			switch(status) {
				case "aguardando_pagamento":
					ped.setStatusPedido("Aguardando Pagamento");
				break;
				
				case "pagamento_rejeitado":
					ped.setStatusPedido("Pagamento rejeitado");
				break;

				case "pagamento_sucesso":
					ped.setStatusPedido("Pagamento com sucesso");
				break;
				
				case "aguardando_retirada":
					ped.setStatusPedido("Aguardando retirada");
				break;

				case "pedido_transito":
					ped.setStatusPedido("Pedido em transito");
				break;

				case "pedido_entregue":
					ped.setStatusPedido("Pedido entregue");
				break;
			}
			
		}
		
		resultado.addObject("pedidos", pedidos);

		return resultado;
	}
	
	@GetMapping("/listarProdutos")
	public ModelAndView listarProdutos() {
		ModelAndView resultado = new ModelAndView("estoquista/listarProdutos");
		List<Produto> produtos = produtoRepository.findAll();
		resultado.addObject("produtos", produtos);
		return resultado;
	}
	
	@GetMapping("/editar/{id}")
	public ModelAndView editar(@PathVariable Integer id) {
		ModelAndView resultado = new ModelAndView("estoquista/editar");
		Pedido pedido = pedRepo.buscaPorId(id);
		resultado.addObject("pedido", pedido);
		return resultado;

	}
	
	
	@GetMapping("/editarProduto/{id}")
	public ModelAndView editarProduto(@PathVariable Integer id) {
		ModelAndView resultado = new ModelAndView("estoquista/editarProduto");
		Produto produto = produtoRepository.buscaPorId(id);
		resultado.addObject("produto", produto);
		return resultado;

	}
	
	@Transactional
	@PostMapping("/editar/{pedido.id}")
	public String alterar(
			@ModelAttribute("pedido") Pedido pedido,
			BindingResult bindingResult,
            RedirectAttributes redirAttr) {
		pedRepo.atualizaPedido(pedido.getStatusPedido(), pedido.getId());	
	
		return "redirect:/estoquista/listarPedidos";
	}
	
	@Transactional
	@PostMapping("/editarProduto/{produto.id}")
	public String alterarProduto(
			@ModelAttribute("produto") Produto produto,
			BindingResult bindingResult,
            RedirectAttributes redirAttr) {
		System.out.println("\n\n teste vamos ver" +produto.getQuantidadeProduto()+ " id: " +produto.getId());
		produtoRepository.atualizaProduto(produto.getQuantidadeProduto(), produto.getId());
	
		return "redirect:/estoquista/listarProdutos";
	}
}
