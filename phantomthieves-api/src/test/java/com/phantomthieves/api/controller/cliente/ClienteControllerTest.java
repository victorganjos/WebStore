package com.phantomthieves.api.controller.cliente;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.sql.Date;

import org.junit.Test;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.phantomthieves.api.controller.ClienteController;
import com.phantomthieves.api.model.Cliente;
import com.phantomthieves.api.model.Endereco;
import com.phantomthieves.api.repository.ClienteRepository;

public class ClienteControllerTest {

	private static ClienteRepository repository;

	@Test
	public void teste() {

		Cliente cliente = new Cliente();
		ClienteController clienteController = new ClienteController();
		List<Endereco> endereco = new ArrayList<Endereco>();
		Endereco end = new Endereco();
		end.setLogradouro("adiasdij");
		endereco.add(end);
		
		//cliente.setId(3);
	
		System.out.println(cliente.getName());
		repository.deleteById(2);;

	}

}
