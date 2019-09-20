package com.phantomthieves.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DetalheProdutoController {
	
	@RequestMapping("/produtos/")
	public String detalheProduto(Model model) {
		return "produtos/detalheProduto2";
	}
}
