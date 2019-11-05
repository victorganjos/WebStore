package com.phantomthieves.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.phantomthieves.api.model.Cliente;
import com.phantomthieves.api.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
	
	@Query(value = "SELECT * FROM PEDIDO ORDER BY COD_PEDIDO DESC LIMIT 1;", nativeQuery = true)
	Pedido findByUltId();
}
