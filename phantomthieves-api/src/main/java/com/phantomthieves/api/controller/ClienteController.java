package com.phantomthieves.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.phantomthieves.api.model.Cliente;
import com.phantomthieves.api.model.Pedido;
import com.phantomthieves.api.model.ItemPedido;
import com.phantomthieves.api.model.Produto;
import com.phantomthieves.api.model.Endereco;
import com.phantomthieves.api.model.Usuario;
import com.phantomthieves.api.repository.ClienteRepository;
import com.phantomthieves.api.repository.EnderecoRepository;
import com.phantomthieves.api.repository.UsuarioRepository;
import com.phantomthieves.api.repository.PedidoRepository;
import com.phantomthieves.api.repository.ItemPedRepository;

@Controller
@RequestMapping("/cliente")
public class ClienteController {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private BCryptPasswordEncoder bc;

	@Autowired
	private UsuarioController userControl = new UsuarioController();
	
	@Autowired
	private PedidoRepository pedRepo;

	@Autowired
	private ItemPedRepository itePedRepo;

	@GetMapping("/inserir-dados-cliente")
	public ModelAndView inserir() {
		ModelAndView resultado = new ModelAndView("cliente/inserir-dados-cliente");
		resultado.addObject("cliente", new Cliente());
		resultado.addObject("endereco", new Endereco());
		return resultado;
	}

	@PostMapping("/inserir-dados-cliente")
	public String inserir(@ModelAttribute("cliente") Cliente cliente, @ModelAttribute("endereco") Endereco address) {
		address.setAtivo(1);
		address.setCodCliente(cliente);

		clienteRepository.save(cliente);
		enderecoRepository.save(address);

		return "redirect:/cliente/inserir-usuario-cliente";
	}

	@GetMapping("/inserir-usuario-cliente")
	public ModelAndView inserirUser() {
		Usuario user = new Usuario();
		ModelAndView resultado = new ModelAndView("cliente/inserir-usuario-cliente");
		user.setUser(clienteRepository.findByLast().getUser());
		System.out.println(user.getUser());
		resultado.addObject("usuario", user);
		return resultado;
	}

	@PostMapping("/inserir-usuario-cliente")
	public String inserir(Usuario cliente) {

		userControl.inserir(cliente);

		return "redirect:/";
	}

	@GetMapping("/meus-dados")
	public ModelAndView meusDados() {
		
		Authentication authentication = (Authentication) SecurityContextHolder.getContext().getAuthentication();

		Cliente cliente = clienteRepository.findByUser(authentication.getName());
		
		List<Pedido> pedidos = pedRepo.findAllByCodUsu(cliente.getId());
		
		ModelAndView resultado = new ModelAndView("cliente/meus-dados");
		resultado.addObject("cliente", new Cliente());
		resultado.addObject("user", new Usuario());
		resultado.addObject("endereco", new Endereco());
		resultado.addObject("pedidos", pedidos);
		return resultado;

	}

	@GetMapping("/editar-dados")
	public ModelAndView editarDados() {

		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String userAuthenticated = userDetails.getUsername();
		Cliente userData = new Cliente();
		List<Endereco> enderecos = new ArrayList<Endereco>();
		Endereco endereco = new Endereco();

		try {
			userData = clienteRepository.findByUser(userAuthenticated);
			System.out.println(userData.getName());
			enderecos = enderecoRepository.findByClientId(userData.getId());
			endereco = enderecos.get(0);
		} catch (Exception userNotFound) {
			System.out.println("Usuário não encontrado");
		}

		ModelAndView resultado = new ModelAndView("cliente/editar-dados");
		resultado.addObject("cliente", userData);
		resultado.addObject("endereco", endereco);
		return resultado;

	}

	@PostMapping("/editar-dados")
	public String editarDadosCliente(@ModelAttribute("cliente") Cliente cliente,
			@ModelAttribute("endereco") Endereco address) {

		clienteRepository.updateCliente(cliente.getName(), cliente.getSirName(), Integer.parseInt(cliente.getDdd()),
				cliente.getTelephone(), Integer.parseInt(cliente.getDddContactTwo()), cliente.getTelephoneContactTwo(),
				cliente.getBirthdate(), cliente.getId());

		address.setCodCliente(cliente);
		address.setAtivo(1);
		enderecoRepository.save(address);

		return "redirect:/cliente/meus-dados/";
	}

	@GetMapping("/editar-usuario-cliente")
	public ModelAndView editarUsuario() {

		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String userAuthenticated = userDetails.getUsername();
		Usuario userData = new Usuario();

		try {
			userData = usuarioRepository.findByUsername(userAuthenticated);
			System.out.println(userData.getUser());

		} catch (Exception userNotFound) {
			System.out.println("Usuário não encontrado");
		}

		ModelAndView resultado = new ModelAndView("cliente/editar-usuario-cliente");
		resultado.addObject("usuario", userData);
		return resultado;

	}

	@Transactional
	@PostMapping("/editar-usuario-cliente")
	public String editarDadosUsuario(Usuario cliente) {
		cliente.setPassword(bc.encode(cliente.getPassword()));
		usuarioRepository.save(cliente);

		usuarioRepository.incluiRegra(cliente.getId(), 3);

		return "redirect:/cliente/meus-dados/";
	}

	@GetMapping("/enderecos")
	public ModelAndView enderecos(@ModelAttribute("msg") String msg) {

		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String userAuthenticated = userDetails.getUsername();
		Cliente userData = new Cliente();
		List<Endereco> address = new ArrayList<Endereco>();
		ModelAndView resultado = new ModelAndView("cliente/enderecos");

		try {
			userData = clienteRepository.findByUser(userAuthenticated);
			address = enderecoRepository.findByClientId(userData.getId());

		} catch (Exception userNotFound) {
			System.out.println("Usuário não encontrado");
		}

		if (address.isEmpty()) {
			resultado.addObject("principal", new Endereco());
		} else {
			resultado.addObject("principal", address.get(0));
		}
		resultado.addObject("enderecos", address);
		resultado.addObject("endereco", new Endereco());
		resultado.addObject("msg", msg);

		return resultado;

	}

	@PostMapping("/enderecos/editar-principal")
	public String enderecosEditarPrincipal(Endereco clienteEndereco) {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String userAuthenticated = userDetails.getUsername();
		Cliente cliente = clienteRepository.findByUser(userAuthenticated);

		clienteEndereco.setCodCliente(cliente);
		clienteEndereco.setAtivo(1);
		enderecoRepository.save(clienteEndereco);

		return "redirect:/cliente/enderecos";
	}

	@PostMapping("/enderecos/adicionar")
	public String enderecosAdicionar(Endereco endereco) {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String userAuthenticated = userDetails.getUsername();
		Cliente cliente = clienteRepository.findByUser(userAuthenticated);

		endereco.setCodCliente(cliente);
		endereco.setAtivo(1);
		enderecoRepository.save(endereco);

		return "redirect:/cliente/enderecos";
	}

	@RequestMapping(value = "/enderecos/inativar/{id}")
	@Transactional
	public ModelAndView enderecosInativar(@PathVariable Integer id) {
		List<Endereco> end = new ArrayList<Endereco>();
		end = enderecoRepository.findAll();
			if (end.get(0).getId() == id) {
				return enderecos("Você deve manter ao menos um endereço cadastrado!");
		}
		System.out.println(id);
		enderecoRepository.inativarIdEndereco(id);
		return enderecos("Operação realizada com sucesso!");
	}
	
	@GetMapping("/listarPedidos")
	public ModelAndView listar() {
		ModelAndView resultado = new ModelAndView("cliente/listarPedidos");
		
		Authentication authentication = (Authentication) SecurityContextHolder.getContext().getAuthentication();

		Cliente cliente = clienteRepository.findByUser(authentication.getName());
		
		List<Pedido> pedidos = pedRepo.findAllByCodUsu(cliente.getId());	
		resultado.addObject("pedidos", pedidos);
		
		return resultado;
	}
	
	@PostMapping("/listarPedidos")
	public ModelAndView pesquisar(@RequestParam("pesquisaPed") Integer idPed) {
		ModelAndView resultado = new ModelAndView("cliente/listarPedidos");
		
		Authentication authentication = (Authentication) SecurityContextHolder.getContext().getAuthentication();

		Cliente cliente = clienteRepository.findByUser(authentication.getName());
		
		if(idPed == null) {
			idPed = 0;
		}
		
		if(idPed == 0) {
			List<Pedido> pedido = pedRepo.findAllByCodUsu(cliente.getId());
			resultado.addObject("pedidos", pedido);
		}else {
			Pedido pedido = pedRepo.findAllByCodPed(idPed, cliente.getId());
			resultado.addObject("pedidos", pedido);
		}

		return resultado;
	}
}