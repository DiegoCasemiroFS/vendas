package com.github.DiegoCasemiroFS.vendas.controller;

import com.github.DiegoCasemiroFS.vendas.controller.dto.AtualizacaoStatusPedidoDto;
import com.github.DiegoCasemiroFS.vendas.controller.dto.InformacaoItemPedidoDto;
import com.github.DiegoCasemiroFS.vendas.controller.dto.InformacaoPedidoDto;
import com.github.DiegoCasemiroFS.vendas.controller.dto.PedidoDto;
import com.github.DiegoCasemiroFS.vendas.entity.ItemPedido;
import com.github.DiegoCasemiroFS.vendas.entity.Pedido;
import com.github.DiegoCasemiroFS.vendas.enums.StatusPedido;
import com.github.DiegoCasemiroFS.vendas.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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

    @PatchMapping("/{id}")
    public void updateStatus(
            @PathVariable Long id,
            @RequestBody AtualizacaoStatusPedidoDto atualizacaoStatusPedidoDTO
    ) {
        String novoStatus = atualizacaoStatusPedidoDTO.getNovoStatus();
        pedidoService.updateStatus(id, StatusPedido.valueOf(novoStatus));
    }

    private InformacaoPedidoDto buildInformacaoPedidoDto(Pedido pedido) {
        return InformacaoPedidoDto.builder()
                .id(pedido.getId())
                .dataPedido(pedido.getDataPedido().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .cpf(pedido.getCliente().getCpf())
                .nome(pedido.getCliente().getNome())
                .total(pedido.getTotal())
                .status(pedido.getStatus().name())
                .items(buildInformacaoItemPedidoDto(pedido.getItems()))
                .build();
    }

    private List<InformacaoItemPedidoDto> buildInformacaoItemPedidoDto(List<ItemPedido> items) {
        if (CollectionUtils.isEmpty(items)) {
            return Collections.emptyList();
        }

        return items.stream()
                .map(m -> InformacaoItemPedidoDto.builder()
                        .descricaoProduto(m.getProduto().getDescricao())
                        .precoUnitario(m.getProduto().getPreco())
                        .quantidade(m.getQuantidade())
                        .build()
                )
                .collect(Collectors.toList());
    }
}