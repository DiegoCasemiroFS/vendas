package com.github.DiegoCasemiroFS.vendas.repository;

import com.github.DiegoCasemiroFS.vendas.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    @Query("select p from Pedido p left join fetch p.items where p.id= :id")
    Optional<Pedido> findByIdFetchItens(@Param("id") Long id);
}
