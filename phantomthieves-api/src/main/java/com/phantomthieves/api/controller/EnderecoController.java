package com.phantomthieves.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.phantomthieves.api.model.Endereco;
/*
@Controller
@RequestMapping("/cliente")
public class EnderecoController {
	
	@GetMapping("/inserir-dados-endereco")
	public String showCreateForm(Model model) {
	    AddressCreatorDTO address = new AddressCreatorDTO();
	 
	    for (int i = 1; i <= 3; i++) {
	    	address.addAddress(new Endereco());
	    }
	 
	    model.addAttribute("form", address);
	    return "cliente/inserir-dados-endereco";
	}
	
	@PostMapping("/save")
	public String saveBooks(@ModelAttribute BooksCreationDto form, Model model) {
	    bookService.saveAll(form.getBooks());
	 
	    model.addAttribute("books", bookService.findAll());
	    return "redirect:/books/all";
	}

}
*/