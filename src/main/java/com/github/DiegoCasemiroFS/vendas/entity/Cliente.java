package com.github.DiegoCasemiroFS.vendas.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;

@Getter
@Entity //marca a classe como uma tabela
@NoArgsConstructor
@AllArgsConstructor
@Table (name="cliente") //editar uma tabela
public class Cliente {

    @Id     //usado para definir uma chave primária, e quando eu quiser acessar qualquer informação
            // da minha tabela, eu vou procurar pelo id (pirmary key)
    @Setter
    @Column(name = "id") // especifica o nome da coluna
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", length = 100) //define o nome e o tamanho da coluna
    private String nome;

    @Column(name = "cpf", length = 14)
    private String cpf;

    @Column(name = "rg", length = 10)
    private String rg;
}
