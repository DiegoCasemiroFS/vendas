package com.github.DiegoCasemiroFS.vendas.entity;

import com.github.DiegoCasemiroFS.vendas.enums.StatusPedido;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //    @Setter
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    //    @Setter
    @Column(name = "data_pedido")
    private LocalDate dataPedido;

    //    @Setter
    @Column(precision = 20, scale = 2)
    private BigDecimal total;

    @Setter
    @Enumerated(EnumType.STRING)
    private StatusPedido status;

    @OneToMany(mappedBy = "pedido")
    private List<ItemPedido> items;

//    Pode-se criar quantos construtores forem necessários, para a criação do objeto. Desta forma evita-se o uso de Setter desnecessários
//    public Pedido(Cliente cliente, LocalDate dataPedido, BigDecimal total, StatusPedido status) {
//        this.cliente = cliente;
//        this.dataPedido = dataPedido;
//        this.total = total;
//        this.status = status;
//    }
}
