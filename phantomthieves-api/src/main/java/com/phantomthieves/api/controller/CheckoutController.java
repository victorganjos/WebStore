package com.phantomthieves.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.phantomthieves.api.model.Cliente;
import com.phantomthieves.api.model.Endereco;
import com.phantomthieves.api.model.Imagem;
import com.phantomthieves.api.model.ItemPedido;
import com.phantomthieves.api.model.ItemSelecionado;
import com.phantomthieves.api.model.Pedido;
import com.phantomthieves.api.repository.ClienteRepository;
import com.phantomthieves.api.repository.EnderecoRepository;
import com.phantomthieves.api.repository.ImagemRepository;
import com.phantomthieves.api.repository.ItemPedRepository;
import com.phantomthieves.api.repository.PedidoRepository;

@Controller
@RequestMapping("/carrinho")
@SessionAttributes("itensSelecionados1")
public class CheckoutController {

	@Autowired
	private ClienteRepository cli;

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private EnderecoRepository endereco;

	@Autowired
	private ItemPedRepository itemPedido;
	
	@Autowired
	private ImagemRepository im;
	
	@RequestMapping("/finalizado")
	public ModelAndView volta(@ModelAttribute("itensSelecionados1") List<ItemSelecionado> itensSelecionados1) {
		ModelAndView resultado = new ModelAndView("index");
		List<Imagem> imagem = im.findImg();
		resultado.addObject("imagem", imagem);
		itensSelecionados1.clear();
		return resultado;
	}
	
	@GetMapping("/checkout")
	public ModelAndView inserir(@ModelAttribute("itensSelecionados1") List<ItemSelecionado> itensSelecionados1) {
		ModelAndView resultado = new ModelAndView("carrinho/checkout");
		Double soma = 5.0;
		for (ItemSelecionado item : itensSelecionados1) {
			soma += item.getValorTotal();
		}

		Authentication authentication = (Authentication) SecurityContextHolder.getContext().getAuthentication();

		Cliente cliente = cli.findByUser(authentication.getName());

		List<Endereco> enderecos = endereco.findByClientId(cliente.getId());
		
		resultado.addObject("pedido", new Pedido());
		resultado.addObject("valorTotal", soma);
		resultado.addObject("enderecos", enderecos);
		System.out.println("Vamos: " + cliente.getId() + "\n\n\n\n\n");
		return resultado;
	}

	@GetMapping("/confirmaPedido/{id}/{idVenda}")
	public ModelAndView finalizarCompra(
			@ModelAttribute("itensSelecionados1") List<ItemSelecionado> itensSelecionados1,
			@PathVariable Integer id, @PathVariable Integer idVenda) {
		ModelAndView resultado = new ModelAndView("carrinho/confirmaPedido");
		Authentication authentication = (Authentication) SecurityContextHolder.getContext().getAuthentication();
		Double soma = 5.0;
		
		for (ItemSelecionado item : itensSelecionados1) {
			soma += item.getValorTotal();
		}
		
		
		Endereco end = new Endereco();
		end = endereco.buscaPorId(id);
		resultado.addObject("endereco", end.getAddress());
		resultado.addObject("numero", end.getAddressNumber());
		resultado.addObject("complemento", end.getComplemento());
		resultado.addObject("cep", end.getCep());
		resultado.addObject("valorTotal", soma);
		resultado.addObject("numeroPedido", idVenda);
		for (ItemSelecionado item : itensSelecionados1) {
			soma += item.getValorTotal();
		}

		Cliente cliente = cli.findByUser(authentication.getName());
		/*
		 * resultado.addObject("valorTotal", soma); resultado.addObject("endereco",
		 * cliente.getAddress()); resultado.addObject("numero",
		 * cliente.getAddressNumber()); resultado.addObject("complemento",
		 * cliente.getComplemento()); resultado.addObject("cep", cliente.getCep());
		 */

		return resultado;

	}
	
	@GetMapping("/escolhePagamento")
	public ModelAndView escolhePagamento(@ModelAttribute("itensSelecionados1") List<ItemSelecionado> itensSelecionados1, 
			@ModelAttribute("pedido") Pedido pedido,
			BindingResult bindingResult,
            RedirectAttributes redirAttr) {
		ModelAndView resultado = new ModelAndView("carrinho/escolhePagamento");
		
		Authentication authentication = (Authentication) SecurityContextHolder.getContext().getAuthentication();
		Endereco end = endereco.buscaPorId(pedido.getCodEndereco());
		Cliente cliente = cli.findByUser(authentication.getName());
		
		Double soma = 5.0;
		
		for (ItemSelecionado item : itensSelecionados1) {
			soma += item.getValorTotal();
		}
		
		resultado.addObject("usuario", cliente);
		resultado.addObject("name", cliente.getName());
		resultado.addObject("cpf", cliente.getCpf());
		resultado.addObject("telefone", cliente.getTelephone());
		resultado.addObject("endereco", end.getAddress());
		resultado.addObject("numero", end.getAddressNumber());
		resultado.addObject("complemento", end.getComplemento());
		resultado.addObject("codEndereco", pedido.getCodEndereco());
		resultado.addObject("valor", soma);
		System.out.println("\n\n\n Teste escolhe pedido: " +pedido.getCodEndereco());
		
		return resultado;
		
	}
	
	@PostMapping("/escolhePagamento")
	public ModelAndView salvar(@ModelAttribute("itensSelecionados1") List<ItemSelecionado> itensSelecionados1, 
			@ModelAttribute("pedido") Pedido pedido,
			BindingResult bindingResult,
            RedirectAttributes redirAttr,
            @RequestParam("codigoEndereco") int codigoEndereco) {
		ModelAndView resultado = new ModelAndView("carrinho/escolhePagamento");
		Double valorTotal = 5.0;

		for (ItemSelecionado item : itensSelecionados1) {
			valorTotal += item.getValorTotal();
		}

		Pedido ped = new Pedido();

		ped.setValorTotal(valorTotal);

		Authentication authentication = (Authentication) SecurityContextHolder.getContext().getAuthentication();

		Cliente cliente = cli.findByUser(authentication.getName());

		ped.setCodCliente(cliente.getId());

		
		java.sql.Date dataSql = new java.sql.Date(System.currentTimeMillis());
		ped.setDataPed(dataSql);
		ped.setCodEndereco(codigoEndereco);
		ped.setFormaPagamento(pedido.getFormaPagamento());
		pedidoRepository.save(ped);
		
		System.out.println("\n\n\n TESTE MODEL ATRIBUTE: " +codigoEndereco+ "vamos la " +pedido.getFormaPagamento());

		Pedido ultPed = pedidoRepository.findByUltId();

		for (ItemSelecionado item : itensSelecionados1) {
			ItemPedido itemPed = new ItemPedido();

			itemPed.setCodProduto(item.getItem().getCodProduto().getId());
			itemPed.setQtde(item.getQtCarrinho());
			itemPed.setValor(item.getItem().getPrecoProduto());
			itemPed.setCodPedido(ultPed.getId());

			itemPedido.save(itemPed);
		}
		
	
		resultado.addObject("valorTotal", valorTotal);
		return new ModelAndView("redirect:/carrinho/confirmaPedido/"+codigoEndereco+"/"+ultPed.getId()+"");
	}
	
	
	@GetMapping("/novoEndereco")
	public ModelAndView inserir() {
		ModelAndView resultado = new ModelAndView("carrinho/novoEndereco");
		resultado.addObject("endereco", new Endereco());
		return resultado;
	}

	
	
	@PostMapping("/novoEndereco")
	public String enderecosAdicionar(Endereco novoEndereco) {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String userAuthenticated = userDetails.getUsername();
		Cliente cliente = cli.findByUser(userAuthenticated);
		
		List<Endereco> address = endereco.findAll();
		if (!address.equals(null)) {

			for (int i = 0; i < address.size(); i++) {
				if (address.get(i).getAddress().equals(novoEndereco.getAddress())
						&& address.get(i).getAddressNumber().equals(novoEndereco.getAddressNumber())) {

					novoEndereco.setId(address.get(i).getId());
					novoEndereco.setAtivo(1);
				}
			}
		}
		novoEndereco.setCodCliente(cliente);
		novoEndereco.setAtivo(1);
		endereco.save(novoEndereco);

		return "redirect:/carrinho/checkout";
	}

}