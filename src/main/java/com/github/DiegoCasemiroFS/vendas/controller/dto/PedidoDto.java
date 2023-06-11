package com.github.DiegoCasemiroFS.vendas.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PedidoDto {

    @NotNull(message = "o codigo do cliente deve ser informado.")
    private Long cliente;

    @NotNull(message = "o campo total deve ser informado.")
    private BigDecimal total;

    private List<ItemPedidoDto> itens;
}
