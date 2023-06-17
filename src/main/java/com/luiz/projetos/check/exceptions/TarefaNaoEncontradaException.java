package com.luiz.projetos.check.exceptions;

public class TarefaNaoEncontradaException extends RuntimeException{
    public TarefaNaoEncontradaException() {
        super("Tarefa não encontrada.");
    }
}
