package com.github.DiegoCasemiroFS.vendas.service;

import com.github.DiegoCasemiroFS.vendas.controller.dto.PedidoDto;
import com.github.DiegoCasemiroFS.vendas.entity.Pedido;
import com.github.DiegoCasemiroFS.vendas.enums.StatusPedido;

import java.util.Optional;

public interface PedidoService {

    Pedido include(PedidoDto pedidoDto);

    Optional<Pedido> bringComplete(Long id);

    void updateStatus(Long id, StatusPedido statusPedido);
}
