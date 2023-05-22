package com.github.DiegoCasemiroFS.vendas.repository;

import com.github.DiegoCasemiroFS.vendas.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // A partir da versão 2.5.x não é mais obrigatório esta anotação
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
