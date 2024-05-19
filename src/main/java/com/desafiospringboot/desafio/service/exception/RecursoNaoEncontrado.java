package com.desafiospringboot.desafio.service.exception;

public class RecursoNaoEncontrado extends RuntimeException {

    public RecursoNaoEncontrado(String mensagem) {
        super(mensagem);
    }
}
