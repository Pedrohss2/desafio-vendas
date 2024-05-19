package com.desafiospringboot.desafio.service.exception;

public class BancoDeDadosException extends RuntimeException {

    public BancoDeDadosException(String mensagem) {
        super(mensagem);
    }
}
