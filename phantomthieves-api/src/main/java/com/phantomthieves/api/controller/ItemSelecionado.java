/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.phantomthieves.api.controller;


import java.io.Serializable;

import com.phantomthieves.api.model.Produto;
import com.phantomthieves.api.model.Imagem;


/**
 *
 * @author CaioHenrique
 */
public class ItemSelecionado implements Serializable {

	private static final long serialVersionUID = 1L;

	private Imagem item;
    
    public ItemSelecionado() {
        
    }
    
    public ItemSelecionado(Imagem item) {
        this.item = item;
    }

    public Imagem getItem() {
        return item;
    }

    public void setItem(Imagem item) {
        this.item = item;
    }
}
