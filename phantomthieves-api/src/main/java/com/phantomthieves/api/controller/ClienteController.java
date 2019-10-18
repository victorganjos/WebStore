package com.phantomthieves.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.phantomthieves.api.model.Cliente;
import com.phantomthieves.api.model.Endereco;
import com.phantomthieves.api.model.Usuario;
import com.phantomthieves.api.repository.ClienteRepository;
import com.phantomthieves.api.repository.EnderecoRepository;
import com.phantomthieves.api.repository.UsuarioRepository;

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

	@GetMapping("/inserir-dados-cliente")
	public ModelAndView inserir() {
		ModelAndView resultado = new ModelAndView("cliente/inserir-dados-cliente");
		resultado.addObject("cliente", new Cliente());
		return resultado;
	}

	@PostMapping("/inserir-dados-cliente")
	public String inserir(Cliente cliente) {
		clienteRepository.save(cliente);

		return "redirect:/cliente/inserir-usuario-cliente";
	}

	@GetMapping("/inserir-usuario-cliente")
	public ModelAndView inserirUser() {
		Usuario user = new Usuario();
		ModelAndView resultado = new ModelAndView("cliente/inserir-usuario-cliente");
		user.setUser(clienteRepository.findByLast().getUser());
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

		ModelAndView resultado = new ModelAndView("cliente/meus-dados");
		resultado.addObject("cliente", new Cliente());
		resultado.addObject("user", new Usuario());
		resultado.addObject("endereco", new Endereco());
		return resultado;

	}

	@GetMapping("/editar-dados")
	public ModelAndView editarDados() {

		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String userAuthenticated = userDetails.getUsername();
		Cliente userData = new Cliente();

		try {
			userData = clienteRepository.findByUser(userAuthenticated);
			System.out.println(userData.getName());

		} catch (Exception userNotFound) {
			System.out.println("Usuário não encontrado");
		}

		ModelAndView resultado = new ModelAndView("cliente/editar-dados");
		resultado.addObject("cliente", userData);
		return resultado;

	}

	@PostMapping("/editar-dados")
	public String editarDadosCliente(Cliente cliente) {

		clienteRepository.save(cliente);

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

	@PostMapping("/editar-usuario-cliente")
	public String editarDadosUsuario(Usuario cliente) {

		usuarioRepository.save(cliente);

		return "redirect:/cliente/meus-dados/";
	}

	@GetMapping("/enderecos")
	public ModelAndView enderecos() {

		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String userAuthenticated = userDetails.getUsername();
		Cliente userData = new Cliente();
		List<Endereco> address = new ArrayList<Endereco>();
		try {
			userData = clienteRepository.findByUser(userAuthenticated);
			System.out.println(userData.getId());

			address = enderecoRepository.findByClientId(userData.getId());

			System.out.println(address.get(0).getId());
			if (address.equals(null)) {
				address.add(new Endereco());
				address.get(0).setId(userData.getId());
				address.get(0).setCep(userData.getCep());
				address.get(0).setLogradouro(userData.getAddress());
				address.get(0).setNumero(userData.getAddressNumber());
				address.get(0).setBairro(userData.getBairro());
				address.get(0).setCity(userData.getCity());
				address.get(0).setComplemento(userData.getComplemento());
				address.get(0).setUf(userData.getUf());
			}

		} catch (Exception userNotFound) {
			System.out.println("Usuário não encontrado");
		}

		ModelAndView resultado = new ModelAndView("cliente/enderecos");
		resultado.addObject("clienteEndereco", userData);
		resultado.addObject("enderecos", address);
		resultado.addObject("endereco", new Endereco());

		return resultado;

	}

	@PostMapping("/enderecos/editar-principal")
	public String enderecosEditarPrincipal(Cliente clienteEndereco) {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String userAuthenticated = userDetails.getUsername();
		Cliente cliente = clienteRepository.findByUser(userAuthenticated);

		cliente.setAddress(clienteEndereco.getAddress());
		cliente.setAddressNumber(clienteEndereco.getAddressNumber());
		cliente.setCep(clienteEndereco.getCep());

		clienteRepository.save(cliente);

		return "redirect:/cliente/meus-dados/";
	}

	@PostMapping("/enderecos/adicionar")
	public String enderecosAdicionar(Endereco endereco) {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String userAuthenticated = userDetails.getUsername();
		Cliente cliente = clienteRepository.findByUser(userAuthenticated);

		List<Endereco> address = enderecoRepository.findAll();
		if (!address.equals(null)) {

			for (int i = 0; i < address.size(); i++) {
				if (address.get(i).getAddress().equals(endereco.getAddress())
						&& address.get(i).getAddressNumber().equals(endereco.getAddressNumber())) {

					endereco.setId(address.get(i).getId());
					endereco.setAtivo(1);
				}
			}
		}
		endereco.setCodCliente(cliente);
		endereco.setAtivo(1);
		enderecoRepository.save(endereco);

		return "redirect:/cliente/meus-dados/";
	}

	@RequestMapping(value = "/enderecos/inativar/{id}")
	@Transactional
	public String enderecosInativar(@PathVariable Integer id) {
		System.out.println(id);
		enderecoRepository.inativarIdEndereco(id);
		return "redirect:/cliente/meus-dados/";
	}

}
