package com.luiz.projetos.check.controllers;

import com.luiz.projetos.check.dto.ApiErrors;
import com.luiz.projetos.check.exceptions.RegraDeNegocioException;
import com.luiz.projetos.check.exceptions.SenhaInvalidaException;
import com.luiz.projetos.check.exceptions.TarefaNaoEncontradaException;
import com.luiz.projetos.check.exceptions.UsuarioNaoEncontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ApplicationControllerAdvice {
    @ExceptionHandler(UsuarioNaoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private ApiErrors handleUsuarioNaoEncontradoException(UsuarioNaoEncontradoException ex){
        return new ApiErrors(ex.getMessage());
    }

    @ExceptionHandler(RegraDeNegocioException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private ApiErrors handleRegraDeNegocioException(RegraDeNegocioException ex){
        return new ApiErrors(ex.getMessage());
    }

    @ExceptionHandler(TarefaNaoEncontradaException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private ApiErrors handleTarefaNaoEncontradaException(TarefaNaoEncontradaException ex){
        return new ApiErrors(ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private ApiErrors handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        List<String> errorsList = ex.getBindingResult().getAllErrors().stream()
                .map(erros -> erros.getDefaultMessage())
                .collect(Collectors.toList());

        return new ApiErrors(errorsList);
    }

    @ExceptionHandler(SenhaInvalidaException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    private ApiErrors handleMethodArgumentNotValidException(SenhaInvalidaException ex){
        return new ApiErrors(ex.getMessage());
    }
}
