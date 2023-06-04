package com.luiz.projetos.check.dto;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

public class ArrayErrors {

    @Getter
    private List<String> errors;

    public ArrayErrors(String mensagem){
        this.errors = Arrays.asList(mensagem);
    }
}
