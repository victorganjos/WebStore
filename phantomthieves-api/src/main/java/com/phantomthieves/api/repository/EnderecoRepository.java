package com.phantomthieves.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import com.phantomthieves.api.model.Endereco;

public interface EnderecoRepository  extends JpaRepository<Endereco, Integer> {
	
	@Query(value = "SELECT * FROM ENDERECO_ENTREGA WHERE COD_CLIENTE = ? AND ATIVO = 1;", nativeQuery = true)
	List<Endereco> findByClientId(int id);

	@Modifying
	@Query(value ="UPDATE ENDERECO_ENTREGA SET ATIVO = FALSE WHERE COD_ENDERECO = ?;", nativeQuery = true)
	void inativarIdEndereco(Integer id);
}
