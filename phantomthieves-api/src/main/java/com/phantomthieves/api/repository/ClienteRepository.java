package com.phantomthieves.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.phantomthieves.api.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

	@Query(value = "SELECT * FROM CLIENTE WHERE COD_CLIENTE = LAST_INSERT_ID();", nativeQuery = true)
	Cliente findByLast();
	
	@Query(value = "SELECT * FROM CLIENTE WHERE USER_CLIENTE = ?;", nativeQuery = true)
	Cliente findByUser(String username);

}
