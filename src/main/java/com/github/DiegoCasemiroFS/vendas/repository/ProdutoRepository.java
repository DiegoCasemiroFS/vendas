package com.github.DiegoCasemiroFS.vendas.repository;

import com.github.DiegoCasemiroFS.vendas.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository //a partir da versão 2.5.x não é mais obrigatorio
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
