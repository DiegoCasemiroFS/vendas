package com.github.DiegoCasemiroFS.vendas.repository;

import com.github.DiegoCasemiroFS.vendas.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository <Cliente, Long> {

}
