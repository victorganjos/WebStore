package com.phantomthieves.api.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

@Entity
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "COD_PEDIDO")
	private Integer id;

	@Column(name = "DATAPED")
	private Date dataPed;
	
	@Column(name = "VALOR_TOTAL")
	private Double valorTotal;
	
	@Column(name = "COD_ENDERECO")
	private Integer codEndereco;
	
	@Column(name = "COD_CLIENTE")
	private Integer  codCliente;

	public Pedido() {
		
	}
	
	
	public Pedido(Integer id, Date dataPed, String formaPagamento, Double valorTotal, Integer codEndereco,
			Integer codCliente) {
		super();
		this.id = id;
		this.dataPed = dataPed;
		this.valorTotal = valorTotal;
		this.codEndereco = codEndereco;
		this.codCliente = codCliente;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDataPed() {
		return dataPed;
	}

	public void setDataPed(Date dataPed) {
		this.dataPed = dataPed;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Integer getCodEndereco() {
		return codEndereco;
	}

	public void setCodEndereco(Integer codEndereco) {
		this.codEndereco = codEndereco;
	}

	public Integer getCodCliente() {
		return codCliente;
	}

	public void setCodCliente(Integer codCliente) {
		this.codCliente = codCliente;
	}

}
