package com.github.DiegoCasemiroFS.vendas.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "usuarios")

public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty (message = "o campo login é obrigatorio.")
    private String login;

    @NotEmpty (message = "o campo senha é obrigatorio.")
    private String senha;
    private boolean admin;
}
