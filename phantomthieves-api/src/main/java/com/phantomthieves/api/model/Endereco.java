package com.phantomthieves.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

@Entity
public class Endereco {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "COD_ENDERECO")
	private Integer id;

	@Column(name = "LOGRADOURO", length = 150, nullable = false)
	@NotNull(message = "O logradouro é obrigatório")
	@Length(max = 150, min = 5, message = "O logradouro do usuário deve conter entrer 5 e 150 caracteres")
	private String address;

	@Column(name = "CEP", length = 9, nullable = false)
	@NotNull(message = "O CEP é obrigatório")
	@Length(max = 9, min = 8, message = "O campo CEP deve ser preenchido corretamente (Excesso ou ausência de dígitos)")
	private String cep;

	@Column(name = "NUMERO_RES", nullable = false)
	@NotNull(message = "O número da residência é obrigatório")
	@Length(min = 1, message = "O campo de número da residência deve ser preenchido corretamente (Excesso ou ausência de dígitos)")
	private String addressNumber;

	// private Cliente cod_cliente;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLogradouro() {
		return address;
	}

	public void setLogradouro(String logradouro) {
		this.address = logradouro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getNumero() {
		return addressNumber;
	}

	public void setNumero(String numero) {
		this.addressNumber = numero;
	}

}
