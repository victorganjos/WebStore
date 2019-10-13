package com.phantomthieves.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="IMAGEM_PRODUTO")
public class Imagem {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "COD_IMAGEM")
	private Integer id;
	
	@Column(name = "NOME_ARQUIVO")
	private String nomeArquivo;
	
	@Column(name = "LOCAL_ARQUIVO")
	private String localArquivo;
	
	@Column(name = "PRECO_PRODUTO")
	private Double precoProduto;	

	@Column(name = "NOME_PRODUTO")
	private String nomeProduto;
	
	
	@ManyToOne
	@JoinColumn(name= "COD_PRODUTO", referencedColumnName = "COD_PRODUTO")
	private Produto codProduto;
	
	public Imagem() {
		
	}
	
	public Imagem(String nomeArquivo, String localArquivo) {
		super();
		this.nomeArquivo = nomeArquivo;
		this.localArquivo = localArquivo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getPrecoProduto() {
		return precoProduto;
	}

	public void setPrecoProduto(Double precoProduto) {
		this.precoProduto = precoProduto;
	}
	
	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}
	
	public String getNomeArquivo() {
		return nomeArquivo;
	}

	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}

	public String getLocalArquivo() {
		return localArquivo;
	}

	public void setLocalArquivo(String localArquivo) {
		this.localArquivo = localArquivo;
	}
	
}