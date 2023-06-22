package com.github.DiegoCasemiroFS.vendas.controller.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InformacaoPedidoDto {

    private Long id;
    private String cpf;
    private String nome;
    private BigDecimal total;
    private String dataPedido;
    private String status;
    private List<InformacaoItemPedidoDto> items;

}
