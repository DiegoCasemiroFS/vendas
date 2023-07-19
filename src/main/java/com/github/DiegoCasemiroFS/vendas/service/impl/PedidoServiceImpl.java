package com.github.DiegoCasemiroFS.vendas.service.impl;

import com.github.DiegoCasemiroFS.vendas.controller.dto.InformacaoItemPedidoDto;
import com.github.DiegoCasemiroFS.vendas.controller.dto.InformacaoPedidoDto;
import com.github.DiegoCasemiroFS.vendas.controller.dto.PedidoDto;
import com.github.DiegoCasemiroFS.vendas.entity.Cliente;
import com.github.DiegoCasemiroFS.vendas.entity.ItemPedido;
import com.github.DiegoCasemiroFS.vendas.entity.Pedido;
import com.github.DiegoCasemiroFS.vendas.entity.Produto;
import com.github.DiegoCasemiroFS.vendas.enums.StatusPedido;
import com.github.DiegoCasemiroFS.vendas.exception.PedidoNaoEncontradoException;
import com.github.DiegoCasemiroFS.vendas.repository.ItemPedidoRepository;
import com.github.DiegoCasemiroFS.vendas.repository.PedidoRepository;
import com.github.DiegoCasemiroFS.vendas.service.ClienteService;
import com.github.DiegoCasemiroFS.vendas.service.PedidoService;
import com.github.DiegoCasemiroFS.vendas.service.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {

    private final ClienteService clienteService;
    private final ProdutoService produtoService;
    private final PedidoRepository pedidoRepository;
    private final ItemPedidoRepository itemPedidoRepository;

    @Override
    public Pedido include(PedidoDto pedidoDto) {
        Cliente cliente = clienteService.findById(pedidoDto.getCliente());
        Pedido pedido = buildPedido(pedidoDto, cliente);
        List<ItemPedido> itemsPedido = buildItemPedidos(pedidoDto, pedido);
        pedidoRepository.save(pedido);
        itemPedidoRepository.saveAll(itemsPedido);
        return pedido;
    }

    @Override
    public InformacaoPedidoDto bringComplete(Long id) {
        Optional<Pedido> pedidoRepositoryByIdFetchItens = pedidoRepository.findByIdFetchItens(id);
        return pedidoRepositoryByIdFetchItens.map(
                        m -> buildInformacaoPedidoDto(m)
                )
                .orElseThrow(() -> new PedidoNaoEncontradoException());
    }

    @Override
    public void updateStatus(Long id, StatusPedido statusPedido) {
        pedidoRepository.findById(id)
                .map(pedido -> {
                    pedido.setStatus(statusPedido);
                    return pedidoRepository.save(pedido);
                }).orElseThrow(() -> new PedidoNaoEncontradoException());
    }

    private static Pedido buildPedido(PedidoDto pedidoDto, Cliente cliente) {
        return Pedido.builder()
                .cliente(cliente)
                .dataPedido(LocalDate.now())
                .total(pedidoDto.getTotal())
                .status(StatusPedido.REALIZADO)
                .build();
    }

    private List<ItemPedido> buildItemPedidos(PedidoDto pedidoDto, Pedido pedido) {
        List<ItemPedido> itemsPedido = pedidoDto.getItens()
                .stream()
                .map(
                        m -> {
                            Long idProduto = m.getProduto();
                            Produto produto = produtoService.findById(idProduto);
                            return ItemPedido.builder()
                                    .pedido(pedido)
                                    .produto(produto)
                                    .quantidade(m.getQuantidade())
                                    .build();
                        })
                .collect(Collectors.toList());
        return itemsPedido;
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