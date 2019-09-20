package com.phantomthieves.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.phantomthieves.api.model.Imagem;

public interface ImagemRepository extends JpaRepository<Imagem, Integer>{
	
	@Query(value = "SELECT * FROM PRODUTO ORDER BY COD_IMAGEM DESC LIMIT 1;", nativeQuery = true)
	Imagem findByLast();

}
