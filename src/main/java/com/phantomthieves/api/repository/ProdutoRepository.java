package com.phantomthieves.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.phantomthieves.api.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto,Integer>{
	
	List<Produto> findByNomeProdutoContaining(String nomeProduto);
}
