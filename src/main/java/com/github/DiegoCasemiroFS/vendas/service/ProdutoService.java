package com.github.DiegoCasemiroFS.vendas.service;

import com.github.DiegoCasemiroFS.vendas.entity.Produto;
import com.github.DiegoCasemiroFS.vendas.exception.RegraNegocioException;
import com.github.DiegoCasemiroFS.vendas.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public Produto findById(Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Código de produto inválido."));
    }

    public List<Produto> listAll() {
        return produtoRepository.findAll();
    }

    public Produto include(Produto produto) {
        return produtoRepository.save(produto);
    }

    public Produto update(Long id, Produto produto) {
        return produtoRepository.findById(id)
                .map(f -> {
                    produto.setId(f.getId());
                    produtoRepository.save(produto);
                    return produto;
                })
                .orElseThrow(() -> new RegraNegocioException("Código de produto inválido."));
    }

    public void delete(Long id) {
        produtoRepository.findById(id)
                .map(f -> {
                    produtoRepository.delete(f);
                    return Void.TYPE;
                })
                .orElseThrow(() -> new RegraNegocioException("Código de produto inválido."));
    }
}