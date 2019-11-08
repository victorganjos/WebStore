/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.phantomthieves.api.model;


import java.io.Serializable;


/**
 *
 * @author CaioHenrique
 */
public class ItemSelecionado {

	private static final long serialVersionUID = 1L;

	private Imagem item;
    
	private Integer qtCarrinho = 0;
	
	private Double valorTotal = 0.0;
	
	private Double totItens = 0.0;
	
    public ItemSelecionado() {
        
    }
    
    public ItemSelecionado(Imagem item) {
        this.item = item;
    }
    
    public ItemSelecionado(Imagem item, int qtCarrinho) {
        this.item = item;
        this.qtCarrinho = qtCarrinho;
    }


    public Imagem getItem() {
        return item;
    }

    public void setItem(Imagem item) {
        this.item = item;
    }

	public Integer getQtCarrinho() {
		return qtCarrinho;
	}

	public void setQtCarrinho(Integer qtCarrinho) {
		this.qtCarrinho = qtCarrinho;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Double getTotItens() {
		return totItens;
	}

	public void setTotItens(Double totItens) {
		this.totItens = totItens;
	}

}
