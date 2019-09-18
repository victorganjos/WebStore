/*package com.phantomthieves.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.phantomthieves.api.model.Usuario;
import com.phantomthieves.api.repository.UsuarioRepository;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@GetMapping("/listar")
	public ModelAndView listar() {
		ModelAndView resultado = new ModelAndView("usuarios/listar");
		List<Usuario> usuarios = usuarioRepository.findAll();
		resultado.addObject("usuarios", usuarios);
		return resultado;
	}
	
	
	@GetMapping("/inserir")
	public ModelAndView inserir() {
		ModelAndView resultado = new ModelAndView("usuarios/inserir");
		resultado.addObject("usuario", new Usuario());
		return resultado;
		
	}
	
	@PostMapping("/inserir")
	public String inserir(Usuario usuario) {
		usuarioRepository.save(usuario);
		return "redirect:/usuarios/listar";
	}
	
}
*/