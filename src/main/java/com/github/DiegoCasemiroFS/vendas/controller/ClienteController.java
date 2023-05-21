package com.github.DiegoCasemiroFS.vendas.controller;

import com.github.DiegoCasemiroFS.vendas.entity.Cliente;
import com.github.DiegoCasemiroFS.vendas.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//A classe do tipo controller cuida da interação do usuario com o sistema

//ApiREST - get, put, post, patch, delete; também utilizará os status http (Ex: 200, 404)

@RestController//recebe verbo (get, put, post, patch, delete) e devolve codigo (Ex: 200, 404)
@RequestMapping("v1/api/clientes") //define o prefixo da URL que será tratado por este controlador
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/{id}")
    public Cliente findById(@PathVariable Long id){
        return clienteService.findById(id);
    }

    @GetMapping
    public List<Cliente> ListAll(){
        return clienteService.ListAll();
    }

    @PostMapping
    public Cliente include(@RequestBody Cliente cliente){
        return clienteService.include(cliente);
    }

    @PutMapping("/{id}")
    public Cliente update(@PathVariable Long id, @RequestBody Cliente cliente){
        return clienteService.update(id, cliente);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        clienteService.delete(id);
    }
}
