package com.phantomthieves.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.phantomthieves.api.model.Usuario;
import com.phantomthieves.api.repository.UsuarioRepository;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@GetMapping("/listar")
	public ModelAndView listar() {
		ModelAndView resultado = new ModelAndView("usuarios/listar");
		List<Usuario> usuarios = usuarioRepository.findAllAtivo();
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
	
	@GetMapping("/editar/{id}")
	public ModelAndView editar(@PathVariable Integer id) {
		ModelAndView resultado = new ModelAndView("usuarios/editar");
		Usuario usuario = usuarioRepository.getOne(id);
		resultado.addObject("usuario", usuario);
		return resultado;

	}
	
	@PostMapping("/editar/{usuario.id}")
	public String alterar(Usuario usuario) {
		usuarioRepository.save(usuario);
		return "redirect:/usuarios/listar";
	}
	
	@GetMapping("/excluir/{id}")
	@Transactional
	public String excluir(@PathVariable Integer id) {
		usuarioRepository.deleteDesativo(id);
		return "redirect:/usuarios/listar";

	}
}
