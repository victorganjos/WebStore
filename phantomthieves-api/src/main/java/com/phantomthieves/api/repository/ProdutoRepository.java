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
	
	@Query(value = "SELECT * FROM PRODUTO WHERE 1=2;", nativeQuery = true)
	List<Produto> findAllProduto();
	
	@Modifying
	@Query(value = "UPDATE Produto SET ativo = false where COD_PRODUTO = ?;", nativeQuery = true)
	void deleteDesativo(Integer id);
	
	@Query(value = "SELECT * FROM PRODUTO WHERE NOME_PRODUTO LIKE %?1% AND ATIVO = TRUE", nativeQuery = true)
	List<Produto> findAllNomeProduto(String nome);
	
	@Query(value = "SELECT * FROM PRODUTO WHERE NOME_PRODUTO = ? AND ATIVO = TRUE", nativeQuery = true)
	Produto findByNomeProduto(String nome);
	
	@Query(value = "SELECT * FROM PRODUTO WHERE COD_PRODUTO = ?1", nativeQuery = true)
	Produto findAllByCodigo(Integer codProd);

}
