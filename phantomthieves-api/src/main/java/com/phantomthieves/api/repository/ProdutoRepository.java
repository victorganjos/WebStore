package com.phantomthieves.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.phantomthieves.api.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

	List<Produto> findByNomeProdutoContaining(String nomeProduto);

	@Query(value = "SELECT * FROM PRODUTO ORDER BY COD_PRODUTO DESC LIMIT 1;", nativeQuery = true)
	Produto findByLast();
	
	@Query(value = "SELECT * FROM PRODUTO WHERE ativo = true;", nativeQuery = true)
	List<Produto> findAllAtivo();
	
	@Modifying
	@Query(value = "UPDATE Produto SET ativo = false where COD_PRODUTO = ?;", nativeQuery = true)
	void deleteDesativo(Integer id);

}
