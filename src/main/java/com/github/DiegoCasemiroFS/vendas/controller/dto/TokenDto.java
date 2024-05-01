package com.github.DiegoCasemiroFS.vendas.controller.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TokenDto {

    private String login;
    private String senha;
}
