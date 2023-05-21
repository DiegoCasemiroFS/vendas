package com.github.DiegoCasemiroFS.vendas.service;

import com.github.DiegoCasemiroFS.vendas.entity.Produto;
import com.github.DiegoCasemiroFS.vendas.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public Produto findById(Long id){
        return produtoRepository.findById(id).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado."));
    }

    public List<Produto> ListAll(){
        return produtoRepository.findAll();
    }

    public Produto include(Produto produto){
        return produtoRepository.save(produto);
    }

    public Produto update(Long id, Produto produto){
        findById(id);
        produto.setId(id);
        return produtoRepository.save(produto);
    }

    public void delete(Long id) {
        findById(id);
        produtoRepository.deleteById(id);
    }
}
