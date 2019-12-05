package com.phantomthieves.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

@Entity(name = "ENDERECO_ENTREGA")
public class Endereco {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "COD_ENDERECO")
	private Integer id;

	@Column(name = "LOGRADOURO_ENDERECO", length = 150, nullable = false)
	@NotNull(message = "O logradouro é obrigatório")
	@Length(max = 150, min = 5, message = "O logradouro do usuário deve conter entrer 5 e 150 caracteres")
	private String address;

	@Column(name = "CEP_ENDERECO", length = 10, nullable = false)
	@NotNull(message = "O CEP é obrigatório")
	@Length(max = 9, min = 8, message = "O campo CEP deve ser preenchido corretamente (Excesso ou ausência de dígitos)")
	private String cep;

	@Column(name = "NUMERO_RES_ENDERECO", nullable = false)
	@NotNull(message = "O número da residência é obrigatório")
	@Length(min = 1, message = "O campo de número da residência deve ser preenchido corretamente (Excesso ou ausência de dígitos)")
	private String addressNumber;

	@Column(name = "ATIVO", nullable = false)
	private int ativo;

	@Column(name = "BAIRRO_ENDERECO", nullable = false)
	@NotNull(message = "O bairro da residência é obrigatório")
	private String bairro;

	@Column(name = "COMPLEMENTO_ENDERECO", nullable = false)
	private String complemento;

	@Column(name = "CIDADE_ENDERECO", nullable = false)
	@NotNull(message = "A cidade da residência é obrigatório")
	private String city;

	@Column(name = "UF_ENDERECO", nullable = false)
	@NotNull(message = "O Estado da residência é obrigatório")
	private String uf;

	@ManyToOne
	@JoinColumn(name = "COD_CLIENTE", referencedColumnName = "COD_CLIENTE")
	private Cliente codCliente;

	public Endereco(Integer id,
			@NotNull(message = "O logradouro é obrigatório") @Length(max = 150, min = 5, message = "O logradouro do usuário deve conter entrer 5 e 150 caracteres") String address,
			@NotNull(message = "O CEP é obrigatório") @Length(max = 9, min = 8, message = "O campo CEP deve ser preenchido corretamente (Excesso ou ausência de dígitos)") String cep,
			@NotNull(message = "O número da residência é obrigatório") @Length(min = 1, message = "O campo de número da residência deve ser preenchido corretamente (Excesso ou ausência de dígitos)") String addressNumber,
			int ativo, @NotNull(message = "O bairro da residência é obrigatório") String bairro, String complemento,
			@NotNull(message = "O número da residência é obrigatório") String city,
			@NotNull(message = "O número da residência é obrigatório") String uf, Cliente codCliente) {
		super();
		this.id = id;
		this.address = address;
		this.cep = cep;
		this.addressNumber = addressNumber;
		this.ativo = ativo;
		this.bairro = bairro;
		this.complemento = complemento;
		this.city = city;
		this.uf = uf;
		this.codCliente = codCliente;
	}

	public Endereco() {

	}

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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddressNumber() {
		return addressNumber;
	}

	public void setAddressNumber(String addressNumber) {
		this.addressNumber = addressNumber;
	}

	public Cliente getCodCliente() {
		return codCliente;
	}

	public void setCodCliente(Cliente codCliente) {
		this.codCliente = codCliente;
	}

	public int getAtivo() {
		return ativo;
	}

	public void setAtivo(int ativo) {
		this.ativo = ativo;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

}
