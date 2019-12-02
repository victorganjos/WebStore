package com.phantomthieves.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "ITEM_PEDIDO")
public class ItemPedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "COD_ITEM_PEDIDO")
	private Integer id;

	@Column(name = "QTDE")
	private Integer qtde;
	
	@Column(name = "VALOR")
	private Double valor;
	
	@Column(name = "COD_PRODUTO")
	private Integer codProduto;
	
	@Column(name = "COD_PEDIDO")
	private Integer codPedido;
	
	public ItemPedido() {
		
	}
	
	public ItemPedido(Integer id, Integer qtde, Double valor, Integer codProduto,
			Integer codPedido) {
		super();
		this.id = id;
		this.qtde = qtde;
		this.valor = valor;
		this.codProduto = codProduto;
		this.codPedido = codPedido;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getQtde() {
		return qtde;
	}

	public void setQtde(Integer qtde) {
		this.qtde = qtde;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Integer getCodProduto() {
		return codProduto;
	}

	public void setCodProduto(Integer codProduto) {
		this.codProduto = codProduto;
	}

	public Integer getCodPedido() {
		return codPedido;
	}

	public void setCodPedido(Integer codPedido) {
		this.codPedido = codPedido;
	}

}
