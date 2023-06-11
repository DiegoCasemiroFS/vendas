package com.github.DiegoCasemiroFS.vendas.repository;

import com.github.DiegoCasemiroFS.vendas.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
