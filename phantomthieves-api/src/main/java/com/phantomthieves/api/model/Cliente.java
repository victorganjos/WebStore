package com.phantomthieves.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

@Entity
public class Cliente {

	// Attributes
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "COD_CLIENTE")
	private Integer id;

	@Column(name = "NOME_CLIENTE", length = 100, nullable = false)
	@NotNull(message = "O nome completo  é um campo obrigatório")
	@Length(max = 50, min = 3, message = "O nome completo deve conter entre 7 e 100 caracteres")
	private String name;

	@Column(name = "CPF_CLIENTE", length = 14, nullable = false)
	@NotNull(message = "O CPF é um campo obrigatório")
	@Length(max = 14, min = 11, message = "O CPF válido é obrigatório")
	private String cpf;

	@Column(name = "DDD_CLIENTE", length = 3, nullable = false)
	@NotNull(message = "O DDD é um campo obrigatório")
	@Length(max = 3, min = 2, message = "O DDD deve conter entre 2 e 3 dígitos")
	private String ddd;

	@Column(name = "TELEPHONE_CLIENTE", length = 9, nullable = false)
	@NotNull(message = "O Telefone é um campo obrigatório")
	@Length(max = 9, min = 8, message = "O telefone deve conter entre 8 e 9 dígitos")
	private String telephone;

	@Column(name = "DDD_CONTACT_CLIENTE", length = 3, nullable = false)
	@NotNull(message = "O DDD é um campo obrigatório")
	@Length(max = 3, min = 2, message = "O DDD deve conter entre 2 e 3 dígitos")
	private String dddContactTwo;

	@Column(name = "TELEPHONE_CONTACT_CLIENTE", length = 9, nullable = false)
	@NotNull(message = "O Telefone é um campo obrigatório")
	@Length(max = 9, min = 8, message = "O telefone deve conter entre 8 e 9 dígitos")
	private String telephoneContactTwo;

	@Column(name = "DATA_NASCIMENTO_CLIENTE", length = 10, nullable = false)
	@NotNull(message = "A data de nascimento é um campo obrigatório")
	@Length(min = 10, message = "Selecione um valor válido através do calendário")
	private String birthdate;

	@Column(name = "ENDERECO_CLIENTE", length = 150, nullable = false)
	@NotNull(message = "O endereço é um campo obrigatório")
	@Length(min = 5, message = "Digite um endereço válido")
	private String address;

	@Column(name = "NUMERO_END_CLIENTE", length = 6, nullable = false)
	@NotNull(message = "O número do endereço é um campo obrigátório")
	@Length(min = 1, message = "Digite o número de endereço válido")
	private String addressNumber;

	@Column(name = "CEP_CLIENTE", length = 9, nullable = false)
	@NotNull(message = "O número de cep do endereço é um campo obrigátório")
	@Length(min = 8, message = "Digite um número de cep do endereço válido")
	private String cep;

	@Column(name = "USER_CLIENTE", length = 50, nullable = false)
	@NotNull(message = "O email é obrigatório")
	@Length(max = 50, min = 3, message = "O email de usuário deve conter entrer 3 e 50 caracteres")
	private String user;

	// Constructors
	public Cliente() {
	}

	// Getters and Setters Methods
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getData() {
		return birthdate;
	}

	public void setData(String string) {
		this.birthdate = string;
	}

	public String getDdd() {
		return ddd;
	}

	public void setDdd(String ddd) {
		this.ddd = ddd;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getDddContactTwo() {
		return dddContactTwo;
	}

	public void setDddContactTwo(String dddContactTwo) {
		this.dddContactTwo = dddContactTwo;
	}

	public String getTelephoneContactTwo() {
		return telephoneContactTwo;
	}

	public void setTelephoneContactTwo(String telephoneContactTwo) {
		this.telephoneContactTwo = telephoneContactTwo;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
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

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

}
