package com.phantomthieves.api.dto;

import com.phantomthieves.api.model.Cliente;
import com.phantomthieves.api.model.Endereco;

public class EnderecoDTO {

	public Endereco addressBuilder(Cliente cliente) {

		Endereco endereco = new Endereco();

		endereco.setAddress(cliente.getAddress());
		endereco.setAddressNumber(cliente.getAddressNumber());
		endereco.setAtivo(1);
		endereco.setBairro(cliente.getBairro());
		endereco.setCep(cliente.getCep());
		endereco.setCity(cliente.getCity());
		endereco.setCodCliente(cliente);
		endereco.setComplemento(cliente.getComplemento());
		endereco.setUf(cliente.getUf());

		return endereco;
	}

}
