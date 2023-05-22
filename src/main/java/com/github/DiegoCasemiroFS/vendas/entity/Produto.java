package com.github.DiegoCasemiroFS.vendas.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

// criar repository, controller e service p aula do dia 20/05

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table (name="produto")
public class Produto {

    @Id
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String descricao;

    @Column(name = "preco_unitário")
    private BigDecimal preco;
}
