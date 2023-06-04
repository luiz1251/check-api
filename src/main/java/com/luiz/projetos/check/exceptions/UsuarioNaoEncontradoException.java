package com.luiz.projetos.check.exceptions;

import org.springframework.web.bind.annotation.ExceptionHandler;

public class UsuarioNaoEncontradoException extends RuntimeException {

    public UsuarioNaoEncontradoException() {
        super("Usuário não encontrado.");
    }
}
