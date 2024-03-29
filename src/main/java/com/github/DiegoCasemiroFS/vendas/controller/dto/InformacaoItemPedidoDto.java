package com.github.DiegoCasemiroFS.vendas.controller.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InformacaoItemPedidoDto {

    private String descricaoProduto;
    private BigDecimal precoUnitario;
    private Integer quantidade;
}
