package com.github.DiegoCasemiroFS.vendas.service;

import com.github.DiegoCasemiroFS.vendas.entity.Cliente;
import com.github.DiegoCasemiroFS.vendas.exception.RegraNegocioException;
import com.github.DiegoCasemiroFS.vendas.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public Cliente findById(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Código de cliente inválido."));
    }

    public List<Cliente> ListAll() {
        return clienteRepository.findAll();
    }

    public Cliente include(Cliente cliente){
        return clienteRepository.save(cliente);
    }

    public Cliente update(Long id, Cliente cliente) {
        return clienteRepository.findById(id)
                .map(f -> {
                    cliente.setId(f.getId());
                    clienteRepository.save(cliente);
                    return cliente;
                })
                .orElseThrow(() -> new RegraNegocioException("Código de cliente inválido."));
    }

    public void delete(Long id){
        clienteRepository.findById(id)
        .map(f -> {
            clienteRepository.delete(f);
            return Void.TYPE;
        })
                .orElseThrow(() -> new RegraNegocioException("Código de cliente inválido."));
    }
}
