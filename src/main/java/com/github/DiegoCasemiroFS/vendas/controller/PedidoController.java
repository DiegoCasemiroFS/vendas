package com.github.DiegoCasemiroFS.vendas.controller;

import com.github.DiegoCasemiroFS.vendas.controller.dto.AtualizacaoStatusPedidoDto;
import com.github.DiegoCasemiroFS.vendas.controller.dto.InformacaoPedidoDto;
import com.github.DiegoCasemiroFS.vendas.controller.dto.PedidoDto;
import com.github.DiegoCasemiroFS.vendas.enums.StatusPedido;
import com.github.DiegoCasemiroFS.vendas.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/api/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    @PostMapping
    public Long include(@RequestBody PedidoDto pedidoDto) {
//        Pedido pedido = pedidoService.include(pedidoDto);
//        return pedido.getId();
        return pedidoService.include(pedidoDto).getId();
    }

    @GetMapping("/{id}")
    public InformacaoPedidoDto bringComplete(@PathVariable Long id) {
        return pedidoService.bringComplete(id);
    }

    @PatchMapping("/{id}")
    public void updateStatus(
            @PathVariable Long id,
            @RequestBody AtualizacaoStatusPedidoDto atualizacaoStatusPedidoDTO
    ) {
//        String novoStatus = atualizacaoStatusPedidoDTO.getNovoStatus();
//        pedidoService.updateStatus(id, StatusPedido.valueOf(novoStatus));
        pedidoService.updateStatus(id, StatusPedido.valueOf(atualizacaoStatusPedidoDTO.getNovoStatus()));
    }
}
