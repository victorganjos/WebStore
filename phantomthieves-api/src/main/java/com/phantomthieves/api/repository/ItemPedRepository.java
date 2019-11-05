package com.phantomthieves.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.phantomthieves.api.model.Cliente;
import com.phantomthieves.api.model.ItemPedido;
import com.phantomthieves.api.model.Pedido;

public interface ItemPedRepository extends JpaRepository<ItemPedido, Integer> {

}
