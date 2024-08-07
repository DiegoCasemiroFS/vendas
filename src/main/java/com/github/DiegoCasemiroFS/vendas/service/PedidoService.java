package com.github.DiegoCasemiroFS.vendas.service;

import com.github.DiegoCasemiroFS.vendas.controller.dto.InformacaoPedidoDto;
import com.github.DiegoCasemiroFS.vendas.controller.dto.PedidoDto;
import com.github.DiegoCasemiroFS.vendas.entity.Pedido;
import com.github.DiegoCasemiroFS.vendas.enums.StatusPedido;

public interface PedidoService {

    Pedido include(PedidoDto pedidoDto);

    InformacaoPedidoDto bringComplete(Long id);

    void updateStatus(Long id, StatusPedido statusPedido);
}

