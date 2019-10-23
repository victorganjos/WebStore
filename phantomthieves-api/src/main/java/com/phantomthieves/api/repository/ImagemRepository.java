package com.phantomthieves.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.phantomthieves.api.model.Imagem;

public interface ImagemRepository extends JpaRepository<Imagem, Integer>{
	
	@Query(value = "SELECT * FROM PRODUTO ORDER BY COD_IMAGEM DESC LIMIT 1;", nativeQuery = true)
	Imagem findByLast();
	
	@Query(value = "SELECT * FROM VW_PRODUTO_IMAGEM;", nativeQuery = true)
	List<Imagem> findImg();
	
	@Query(value = "SELECT * FROM VW_PRODUTO_IMAGEM WHERE COD_IMAGEM = ?1 ;", nativeQuery = true)
	Imagem findImgByIdImage(Integer imagemId);
	
	@Query(value = "SELECT * FROM IMAGEM_PRODUTO WHERE COD_PRODUTO = ?1", nativeQuery = true)
	Imagem findImgByIdProd(Integer prodId);
	
	@Modifying
	@Query(value = "UPDATE IMAGEM_PRODUTO SET COD_PRODUTO = ?1 WHERE COD_IMAGEM = ?2 ;", nativeQuery = true)
	void updateImagemProd(Integer idProd, Integer idImg);
}
