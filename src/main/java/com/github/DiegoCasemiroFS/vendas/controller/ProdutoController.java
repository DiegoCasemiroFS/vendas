package com.github.DiegoCasemiroFS.vendas.controller;

import com.github.DiegoCasemiroFS.vendas.entity.Produto;
import com.github.DiegoCasemiroFS.vendas.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/api/produtos")
public class ProdutoController{

    @Autowired
    private ProdutoService produtoService;

    @GetMapping("/{id}")
    public Produto findById(@PathVariable Long id){
    return produtoService.findById(id);
    }

    @GetMapping
    public List<Produto> ListAll(){
        return produtoService.ListAll();
    }

    @PostMapping
    public Produto include(@RequestBody Produto produto){
        return produtoService.include(produto);
    }

    @PutMapping("/{id}")
    public Produto update(@PathVariable Long id, @RequestBody Produto produto){
        return produtoService.update(id, produto);
    }

    @DeleteMapping("/{id}")
    public void delete (@PathVariable Long id){
        produtoService.delete(id);
    }
}
