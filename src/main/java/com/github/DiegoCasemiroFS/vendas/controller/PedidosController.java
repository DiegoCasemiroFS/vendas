package com.github.DiegoCasemiroFS.vendas.controller;

import com.github.DiegoCasemiroFS.vendas.controller.dto.PedidoDto;
import com.github.DiegoCasemiroFS.vendas.entity.Pedido;
import com.github.DiegoCasemiroFS.vendas.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/api/pedidos")
public class PedidosController {

    private final PedidoService pedidoService;

    @PostMapping
    public Long include(@RequestBody PedidoDto pedidoDto) {
        Pedido pedido = pedidoService.include(pedidoDto);
        return pedido.getId();
    }
}