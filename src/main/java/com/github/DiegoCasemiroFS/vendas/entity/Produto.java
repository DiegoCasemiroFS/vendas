package com.github.DiegoCasemiroFS.vendas.entity;

import javax.persistence.*;
import java.math.BigDecimal;

// criar repository, controller e service p aula do dia 20/05

@Entity
@Table (name="produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String descricao;

    @Column(name = "preco_unitário")
    private BigDecimal preco;

    public Produto() {
    }

    public Produto(Long id, String descricao, BigDecimal preco) {
        this.id = id;
        this.descricao = descricao;
        this.preco = preco;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }
}
