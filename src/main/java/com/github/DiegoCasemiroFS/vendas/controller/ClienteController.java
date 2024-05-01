package com.github.DiegoCasemiroFS.vendas.controller;

import com.github.DiegoCasemiroFS.vendas.entity.Cliente;
import com.github.DiegoCasemiroFS.vendas.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/api/clientes")
public class ClienteController {

    private final ClienteService clienteService;

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
