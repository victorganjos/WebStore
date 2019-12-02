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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.phantomthieves.api.model.Pedido;
import com.phantomthieves.api.repository.PedidoRepository;


@Controller
@RequestMapping("/estoquista")
public class EstoqueController {
	
	@Autowired
	private PedidoRepository pedRepo;

	
	@GetMapping("/listarPedidos")
	public ModelAndView listar() {
		ModelAndView resultado = new ModelAndView("estoquista/listarPedidos");
		List<Pedido> pedidos = pedRepo.buscaTodosPedidosDecrescente();
		resultado.addObject("pedidos", pedidos);

		return resultado;
	}
	
	@GetMapping("/editar/{id}")
	public ModelAndView editar(@PathVariable Integer id) {
		ModelAndView resultado = new ModelAndView("estoquista/editar");
		Pedido pedido = pedRepo.buscaPorId(id);
		resultado.addObject("pedido", pedido);
		return resultado;

	}
	
	@Transactional
	@PostMapping("/editar/{pedido.id}")
	public String alterar(
			@ModelAttribute("pedido") Pedido pedido,
			BindingResult bindingResult,
            RedirectAttributes redirAttr) {
		System.out.println("\n\nTeste: " +pedido.getStatusPedido()+ " Teste id: " +pedido.getId());
		pedRepo.atualizaPedido(pedido.getStatusPedido(), pedido.getId());	
	
		return "redirect:/estoquista/listarPedidos";
	}
}
