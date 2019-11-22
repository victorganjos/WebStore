package com.phantomthieves.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.phantomthieves.api.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

	@Query(value = "SELECT * FROM CLIENTE WHERE COD_CLIENTE = LAST_INSERT_ID();", nativeQuery = true)
	Cliente findByLast();

	@Query(value = "SELECT * FROM CLIENTE WHERE USER_CLIENTE = ?;", nativeQuery = true)
	Cliente findByUser(String username);

	@Modifying
	@Transactional
	@Query(value = "UPDATE CLIENTE SET NOME_CLIENTE = ?1 ,SOBRENOME_CLIENTE = ?2 ,DDD_CLIENTE = ?3 ,TELEPHONE_CLIENTE = ?4, DDD_CONTACT_CLIENTE = ?5, TELEPHONE_CONTACT_CLIENTE = ?6 ,DATA_NASCIMENTO_CLIENTE = ?7 WHERE COD_CLIENTE = ?8 ;", nativeQuery = true)
	void updateCliente(String nome, String sobreNome, Integer ddd, String telefone, Integer ddd2, String telefone2,
			String data, Integer id);
}