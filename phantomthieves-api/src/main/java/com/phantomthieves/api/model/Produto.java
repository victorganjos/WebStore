package com.phantomthieves.api.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

@Entity
public class Produto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "COD_PRODUTO")
	private Integer id;
	
	@Column(name = "NOME_PRODUTO", length = 50, nullable = false)
	@NotNull(message = "O nome é obrigatório")
	@Length(max= 50, min = 3, message="O nome deve conter entrer 3 e 50 caracteres")
	private String nomeProduto;
	
	@Column(name = "CATEGORIA_PRODUTO", length = 50, nullable = false)
	@NotNull(message = "A categoria é obrigatório")
	@Length(max= 50, min = 3, message="A categoria deve conter entrer 3 e 50 caracteres")
	private String categoriaProduto;
	
	@Column(name = "DESCRICAO_RESUMIDA")
	private String descricaoResumida;
	
	@Column(name = "DESCRICAO_DETALHADA")
	private String descricaoDetalhada;
	
	@Column(name = "ATIVO")
	private boolean status= true;
	
	@Column(name = "QT_PRODUTO")
	private Integer quantidadeProduto;
	
	@Column(name = "PRECO_PRODUTO")
	private Double precoProduto;

	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name = "COD_PRODUTO") 
	
	private List<Imagem> imagens;
	
	public Produto() {
	
	}

	public Produto(Integer id,
			@NotNull(message = "O nome é obrigatório") @Length(max = 50, min = 3, message = "O nome deve conter entrer 3 e 50 caracteres") String nomeProduto,
			@NotNull(message = "A categoria é obrigatório") @Length(max = 50, min = 3, message = "A categoria deve conter entrer 3 e 50 caracteres") String categoriaProduto,
			String descricaoResumida, String descricaoDetalhada, boolean status, Integer quantidadeProduto,
			Double precoProduto) {
		super();
		this.id = id;
		this.nomeProduto = nomeProduto;
		this.categoriaProduto = categoriaProduto;
		this.descricaoResumida = descricaoResumida;
		this.descricaoDetalhada = descricaoDetalhada;
		this.status = status;
		this.quantidadeProduto = quantidadeProduto;
		this.precoProduto = precoProduto;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	public String getCategoriaProduto() {
		return categoriaProduto;
	}

	public void setCategoriaProduto(String categoriaProduto) {
		this.categoriaProduto = categoriaProduto;
	}

	public String getDescricaoResumida() {
		return descricaoResumida;
	}

	public void setDescricaoResumida(String descricaoResumida) {
		this.descricaoResumida = descricaoResumida;
	}

	public String getDescricaoDetalhada() {
		return descricaoDetalhada;
	}

	public void setDescricaoDetalhada(String descricaoDetalhada) {
		this.descricaoDetalhada = descricaoDetalhada;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Integer getQuantidadeProduto() {
		return quantidadeProduto;
	}

	public void setQuantidadeProduto(Integer quantidadeProduto) {
		this.quantidadeProduto = quantidadeProduto;
	}

	public Double getPrecoProduto() {
		return precoProduto;
	}

	public void setPrecoProduto(Double precoProduto) {
		this.precoProduto = precoProduto;
	}

	public List<Imagem> getImagens() {
		return imagens;
	}

	public void setImagens(List<Imagem> imagens) {
		this.imagens = imagens;
	}
	
	
	public void addImagem(Imagem theImagem) {
		
		if(imagens == null) {
			imagens = new ArrayList<>();
		}
		
		imagens.add(theImagem);
	}
	
}
