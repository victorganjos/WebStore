package com.phantomthieves.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.phantomthieves.api.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
	
	@Query(value = "SELECT * FROM PEDIDO ORDER BY COD_PEDIDO DESC LIMIT 1;", nativeQuery = true)
	Pedido findByUltId();
	
	@Query(value = "SELECT * FROM PEDIDO WHERE COD_CLIENTE = ?1", nativeQuery = true)
	List<Pedido> findAllByCodUsu(Integer codUsu);
	
	@Query(value = "SELECT * FROM PEDIDO WHERE COD_PEDIDO = ?1 AND COD_CLIENTE = ?2", nativeQuery = true)
	Pedido findAllByCodPed(Integer codPed, Integer codUsu);
	
	@Query(value = "SELECT * FROM PEDIDO;", nativeQuery = true)
	List<Pedido> findAllPed();

}

