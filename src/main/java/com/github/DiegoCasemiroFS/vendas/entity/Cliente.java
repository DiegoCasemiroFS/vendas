package com.github.DiegoCasemiroFS.vendas.entity;

import javax.persistence.*;

@Entity //marca a classe como uma tabela
@Table (name="cliente") //editar uma tabela
public class Cliente {

    @Id     //usado para definir uma chave primária, e quando eu quiser acessar qualquer informação
            // da minha tabela, eu vou procurar pelo id (pirmary key)
    @Column(name = "id") // especifica o nome da coluna
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", length = 100) //define o nome e o tamanho da coluna
    private String nome;

    @Column(name = "cpf", length = 14)
    private String cpf;

    @Column(name = "rg", length = 10)
    private String rg;

    public Cliente() {
    }

    public Cliente(Long id, String nome, String cpf, String rg) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.rg = rg;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }
}
