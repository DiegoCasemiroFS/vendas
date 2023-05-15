package com.github.DiegoCasemiroFS.vendas.service;

import com.github.DiegoCasemiroFS.vendas.entity.Cliente;
import com.github.DiegoCasemiroFS.vendas.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente findById(Long id) {
        return clienteRepository.findById(id).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado."));

    }

    public List<Cliente> ListAll() {
        return clienteRepository.findAll();
    }

    public Cliente include(Cliente cliente){
        return clienteRepository.save(cliente);
    }

    public Cliente update(Long id, Cliente cliente){
        findById(id);
        cliente.setId(id);
        return clienteRepository.save(cliente);
    }

    public void delete(Long id){
        findById(id);
        clienteRepository.deleteById(id);
    }
}
