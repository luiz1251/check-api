package com.luiz.projetos.check.controllers;

import com.luiz.projetos.check.dto.ApiErrors;
import com.luiz.projetos.check.exceptions.UsuarioNaoEncontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationControllerAdvice {
    @ExceptionHandler(UsuarioNaoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private ApiErrors handleUsuarioNaoEncontradoException(UsuarioNaoEncontradoException ex){
        return new ApiErrors(ex.getMessage());
    }
}
