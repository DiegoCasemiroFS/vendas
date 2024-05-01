package com.github.DiegoCasemiroFS.vendas.controller.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CredentialDto {

    private String login;
    private String senha;
}
