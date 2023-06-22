package com.github.DiegoCasemiroFS.vendas.exception;

public class PedidoNaoEncontradoException extends RuntimeException{

    public PedidoNaoEncontradoException(){
        super("Pedido não encontrado.");
    }
}
