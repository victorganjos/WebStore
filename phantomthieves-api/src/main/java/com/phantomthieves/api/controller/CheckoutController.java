package com.phantomthieves.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.phantomthieves.api.model.Cliente;
import com.phantomthieves.api.model.Endereco;
import com.phantomthieves.api.model.ItemPedido;
import com.phantomthieves.api.model.ItemSelecionado;
import com.phantomthieves.api.model.Pedido;
import com.phantomthieves.api.repository.ClienteRepository;
import com.phantomthieves.api.repository.EnderecoRepository;
import com.phantomthieves.api.repository.ItemPedRepository;
import com.phantomthieves.api.repository.PedidoRepository;

@Controller
@RequestMapping("/carrinho")
@SessionAttributes("itensSelecionados1")
public class CheckoutController {
	
	@Autowired
	private ClienteRepository cli;
	
	@Autowired
	private PedidoRepository pedido;
	
	@Autowired
	private EnderecoRepository endereco;

	@Autowired
	private ItemPedRepository itemPedido;
	
	@GetMapping("/checkout")
	public ModelAndView inserir(@ModelAttribute("itensSelecionados1") List<ItemSelecionado> itensSelecionados1) {
		ModelAndView resultado = new ModelAndView("carrinho/checkout");
		
		Authentication authentication = (Authentication) SecurityContextHolder.getContext().getAuthentication();
		
		Cliente cliente= cli.findByUser(authentication.getName());
		
		List<Endereco> enderecos = endereco.findByClientId(cliente.getId());

		resultado.addObject("enderecoPrincipal", cliente.getAddress());
		resultado.addObject("numeroEnderecoPrincipal", cliente.getAddressNumber());
		resultado.addObject("enderecos", enderecos);
		System.out.println("Vamos: "+ cliente.getId()+"\n\n\n\n\n");
		return resultado; 
	}
	
	
	@GetMapping("/confirmaPedido")
	public ModelAndView finalizarCompra(@ModelAttribute("itensSelecionados1") List<ItemSelecionado> itensSelecionados1) {
		ModelAndView resultado = new ModelAndView("carrinho/confirmaPedido");
		Authentication authentication = (Authentication) SecurityContextHolder.getContext().getAuthentication();
		
		Cliente cliente= cli.findByUser(authentication.getName());

		resultado.addObject("endereco", cliente.getAddress());
		resultado.addObject("numero", cliente.getAddressNumber());
		resultado.addObject("complemento", cliente.getComplemento());
		resultado.addObject("cep", cliente.getCep());
		return resultado; 
	}

	@PostMapping("/checkout")
	public ModelAndView salvar(@ModelAttribute("itensSelecionados1") List<ItemSelecionado> itensSelecionados1) {
		ModelAndView resultado = new ModelAndView("carrinho/checkout");
		
		double valorTotal = 0;
		
		for (ItemSelecionado item : itensSelecionados1) {
			valorTotal = valorTotal + item.getValorTotal();  			
		}
		
		Pedido ped = new Pedido();

		ped.setValorTotal(valorTotal);
		
		Authentication authentication = (Authentication) SecurityContextHolder.getContext().getAuthentication();
		
		Cliente cliente= cli.findByUser(authentication.getName());
		
		ped.setCodCliente(cliente.getId());
		
		pedido.save(ped);
		
		Pedido ultPed = pedido.findByUltId();
		
		for (ItemSelecionado item : itensSelecionados1) {
			ItemPedido itemPed = new ItemPedido();  		
			
			itemPed.setCodProduto(item.getItem().getCodProduto().getId());
			itemPed.setQtde(item.getQtCarrinho());
			itemPed.setValor(item.getItem().getPrecoProduto());
			itemPed.setCodPedido(ultPed.getId());
			
			itemPedido.save(itemPed);
		}
		
		
		
		return new ModelAndView("redirect:/");
	}

}