package com.phantomthieves.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.phantomthieves.api.model.ItemPedido;

public interface ItemPedRepository extends JpaRepository<ItemPedido, Integer> {
	@Query(value = "SELECT * FROM ITEM_PEDIDO WHERE COD_PEDIDO = ?1", nativeQuery = true)
	List<ItemPedido> findAllByCodPed(Integer codPed);
}
