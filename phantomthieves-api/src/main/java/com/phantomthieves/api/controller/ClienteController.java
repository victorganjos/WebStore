package com.phantomthieves.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.phantomthieves.api.model.Cliente;
import com.phantomthieves.api.model.Endereco;
import com.phantomthieves.api.model.Usuario;
import com.phantomthieves.api.repository.ClienteRepository;
import com.phantomthieves.api.repository.UsuarioRepository;

@Controller
@RequestMapping("/cliente")
public class ClienteController {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private BCryptPasswordEncoder bc;

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

		cliente.setPassword(bc.encode(cliente.getPassword()));
		usuarioRepository.save(cliente);

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
	
	//
	
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

}
