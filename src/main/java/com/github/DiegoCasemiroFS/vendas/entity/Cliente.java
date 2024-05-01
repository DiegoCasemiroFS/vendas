package com.github.DiegoCasemiroFS.vendas.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
@Entity //marca a classe como uma tabela
@NoArgsConstructor
@AllArgsConstructor
@Table (name="cliente") //editar uma tabela
public class Cliente {

    @Id     //usado para definir uma chave primária, e quando eu quiser acessar qualquer informação da minha tabela, eu vou procurar pelo id (pirmary key)
    @Setter
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    @NotEmpty(message = "Campo nome e obrigatório")
    @NotNull(message = "Campo nome não pode ser nulo")
    private String nome;

    @Column(length = 80)
    @Email(message = "Informe um email valido")
    @NotEmpty(message = "Campo email e obrigatório")
    @NotNull(message = "Campo email não deve ser nulo")
    private String email;

    @Column(length = 14)
    @CPF(message = "Informe um cpf valido")
    @NotEmpty(message = "Campo CPF e obrigatório")
    @NotNull(message = "Campo CPF não deve ser nulo")
    private String cpf;

    @Column(length = 10)
    @NotEmpty(message = "Campo RG e obrigatório")
    @NotNull(message = "Campo RG não deve ser nulo")
    private String rg;

    @JsonIgnore
    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY)
    private Set<Pedido> pedidos;
}
